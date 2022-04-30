package br.com.training.service.bet;

import br.com.training.dto.bet.BetConfigurationResponseDto;
import br.com.training.dto.bet.BetRequestDto;
import br.com.training.dto.bet.BetResponseDto;
import br.com.training.exception.bet.BetAlreadyExistsException;
import br.com.training.exception.bet.BetNotFoundException;
import br.com.training.exception.bet.InvalidBetException;
import br.com.training.exception.user.UserNotFoundException;
import br.com.training.model.bet.Bet;
import br.com.training.model.bet.BetPerUser;
import br.com.training.model.user.User;
import br.com.training.repository.bet.BetConfigurationRepository;
import br.com.training.repository.bet.BetPerUserRepository;
import br.com.training.repository.bet.BetRepository;
import br.com.training.repository.bet.BetTypeRepository;
import br.com.training.repository.user.UserRepository;
import br.com.training.utils.BetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BetServiceImpl implements BetService {

    public static final String MSG_BET_NOT_FOUND = "Aposta não encontrada na base de dados!";

    private final BetRepository betRepository;
    private final BetPerUserRepository betPerUserRepository;
    private final UserRepository userRepository;
    private final BetConfigurationRepository betConfigurationRepository;
    private final BetTypeRepository betTypeRepository;

    public BetServiceImpl(BetRepository betRepository, BetPerUserRepository betPerUserRepository, UserRepository userRepository, BetConfigurationRepository betConfigurationRepository, BetTypeRepository betTypeRepository) {
        this.betRepository = betRepository;
        this.betPerUserRepository = betPerUserRepository;
        this.userRepository = userRepository;
        this.betConfigurationRepository = betConfigurationRepository;
        this.betTypeRepository = betTypeRepository;
    }

    @Override
    public ResponseEntity<HttpStatus> salvarAposta(String betName, BetRequestDto betRequestDto) {
        log.info("Salvando a aposta do usuário: " + betRequestDto.getCpf());

        verificarNumerosQuantidadeAposta(betName, betRequestDto);
        isUsuarioExistente(betRequestDto.getCpf());
        Collections.sort(betRequestDto.getNumbers());
        isApostaNova(betRequestDto.getNumbers(), betRequestDto.getCpf());

        Bet bet = new Bet(BetUtils.formatNumbers(betRequestDto.getNumbers().toString()), betRequestDto.getCreatedAt());

        betRepository.save(bet);
        betPerUserRepository.save(new BetPerUser(userRepository.findByCpf(betRequestDto.getCpf()), bet));

        log.info("Aposta salva no banco de dados com êxito.");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @Override
    public ResponseEntity<HttpStatus> deletarAposta(Long id) {
        log.info("Deletando a aposta de ID: " + id);

        Bet bet = betRepository.findById(id).orElseThrow(() -> new BetNotFoundException(MSG_BET_NOT_FOUND));
        betPerUserRepository.deleteByBet(bet.getBetId());
        betRepository.deleteById(bet.getBetId());

        log.info("A seguinte aposta foi deletada do banco de dados: " + bet);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<BetResponseDto> buscarAposta(Long id) {

        BetPerUser betPerUser = betPerUserRepository.findByBet(id).orElseThrow(() -> new BetNotFoundException(MSG_BET_NOT_FOUND));

        BetResponseDto responseDto = new BetResponseDto(betPerUser.getUser().getCpf(),
                BetUtils.transformStringInArray(betPerUser.getBet().getNumbers()),
                betPerUser.getBet().getCreatedAt());

        log.info("Chegou até o final do método BUSCAR APOSTA.");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<BetResponseDto>> buscarTodasApostas() {
        log.info("Buscando todas as apostas na base...");
        Set<BetPerUser> betPerUsers = new HashSet<>(betPerUserRepository.findAll());

        if(betPerUsers.isEmpty()){
            log.error("Lista está vazia!");
            throw new BetNotFoundException(MSG_BET_NOT_FOUND);
        }

        Set<BetResponseDto> responseDtos = betPerUsers
                .stream()
                .map(betPerUser -> new BetResponseDto(betPerUser.getUser().getCpf(), BetUtils.transformStringInArray(betPerUser.getBet().getNumbers()), betPerUser.getBet().getCreatedAt()))
                .collect(Collectors.toSet());

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    private void isUsuarioExistente(String cpf) {
        User user = userRepository.findByCpf(cpf);

        if (user != null){
            log.info("Usuário existe, seguindo com o fluxo...");
        } else {
            throw new UserNotFoundException("Usuário não encontrado!");
        }
    }

    private void isApostaNova(ArrayList<Integer> numbers, String cpf) {
        log.info("Procurando pelos números...");
        log.info("Numbers: " + numbers);

        HashSet<Bet> betSet = betRepository.getBetByNumbersString(BetUtils.formatNumbers(numbers.toString()), cpf);

        log.info("Encontrou ou não a aposta de acordo com os números...");
        log.info("betSet is empty? : " + betSet.isEmpty());

        if (!betSet.isEmpty()){
            throw new BetAlreadyExistsException("Aposta já existe para o usuário: " + cpf);
        }
    }

    private void verificarNumerosQuantidadeAposta(String betName, BetRequestDto betRequestDto) {
        BetConfigurationServiceImpl betConfigurationService = new BetConfigurationServiceImpl(betConfigurationRepository, betTypeRepository);
        BetConfigurationResponseDto betConfigurationResponseDto = betConfigurationService.buscarConfiguracaoAposta(betName);

        for (Map.Entry<String, Integer> entry: betConfigurationResponseDto.getParams().entrySet()) {

            if(entry.getKey().endsWith("max_number")){
                Optional<Integer> max = betRequestDto.getNumbers().stream().max(Integer::compareTo);
                if(entry.getValue() < max.get()) throw new InvalidBetException("O número apostado não pode ser acima do valor parametrizado para a aposta: " + betName);
            }

            if(entry.getKey().endsWith("qtd_number")){
                if(entry.getValue() < betRequestDto.getNumbers().size()) throw new InvalidBetException("Total de números é acima do permitido para a aposta: " + betName);
            }

        }
    }
}

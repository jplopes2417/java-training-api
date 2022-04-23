package br.com.training.service.bet;

import br.com.training.dto.bet.BetRequestDto;
import br.com.training.dto.bet.BetResponseDto;
import br.com.training.exception.bet.BetAlreadyExistsException;
import br.com.training.exception.bet.BetNotFoundException;
import br.com.training.exception.user.UserNotFoundException;
import br.com.training.model.bet.Bet;
import br.com.training.model.bet.BetPerUser;
import br.com.training.model.user.User;
import br.com.training.repository.bet.BetPerUserRepository;
import br.com.training.repository.bet.BetRepository;
import br.com.training.repository.user.UserRepository;
import br.com.training.utils.BetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BetServiceImpl implements BetService {

    public static final String MSG_BET_NOT_FOUND = "Aposta não encontrada na base de dados!";

    private final BetRepository betRepository;
    private final BetPerUserRepository betPerUserRepository;
    private final UserRepository userRepository;

    public BetServiceImpl(BetRepository betRepository, BetPerUserRepository betPerUserRepository, UserRepository userRepository) {
        this.betRepository = betRepository;
        this.betPerUserRepository = betPerUserRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> salvarAposta(BetRequestDto betRequestDto) {

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
    public ResponseEntity<?> deletarAposta(Long id) {
        log.info("Deletando a aposta de ID: " + id);

        Bet bet = betRepository.findById(id).orElseThrow(() -> new BetNotFoundException(MSG_BET_NOT_FOUND));
        betPerUserRepository.deleteByBet(bet.getBetId());
        betRepository.deleteById(bet.getBetId());

        log.info("A seguinte aposta foi deletada do banco de dados: " + bet);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> buscarAposta(Long id) {

        BetPerUser betPerUser = betPerUserRepository.findByBet(id).orElseThrow(() -> new BetNotFoundException(MSG_BET_NOT_FOUND));
        log.info("#################################################################");
        log.info("BET PER USER SEM O OPTIONAL: " + betPerUser.toString());
        log.info("#################################################################");

        BetResponseDto responseDto = new BetResponseDto(betPerUser.getUser().getCpf(),
                BetUtils.transformStringInArray(betPerUser.getBet().getNumbers()),
                betPerUser.getBet().getCreatedAt());

        log.info("Chegou até o final do método BUSCAR APOSTA.");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> buscarTodasApostas() {
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
        log.info("betSet is empty? : " + String.valueOf(betSet.isEmpty()));

        if (!betSet.isEmpty()){
            throw new BetAlreadyExistsException("Aposta já existe para o usuário: " + cpf);
        }
    }
}

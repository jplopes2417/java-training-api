package br.com.training.service.bet;

import br.com.training.dto.bet.BetRequestDto;
import br.com.training.exception.bet.BetAlreadyExistsException;
import br.com.training.exception.user.UserNotFoundException;
import br.com.training.mapper.bet.BetMapper;
import br.com.training.model.Bet;
import br.com.training.model.BetPerUser;
import br.com.training.model.User;
import br.com.training.repository.bet.BetPerUserRepository;
import br.com.training.repository.bet.BetRepository;
import br.com.training.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Slf4j
@Service
public class BetServiceImpl implements BetService {

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
        isApostaNova(betRequestDto.getNumbers(), betRequestDto.getCpf());

//        Bet bet = BetMapper.INSTANCE.toModel(betRequestDto);
        Bet bet = new Bet(betRequestDto.getNumbers().toString(), betRequestDto.getCreatedAt());

        String numbers = betRequestDto.getNumbers().toString();

        log.info(numbers);
        bet.setNumbers(numbers);

        betRepository.save(bet);
        betPerUserRepository.save(new BetPerUser(userRepository.findByCpf(betRequestDto.getCpf()), bet));

        log.info("Salvou a aposta certinho...");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @Override
    public ResponseEntity<?> deletarAposta(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> buscarAposta(String cpf) {
        return null;
    }

    @Override
    public ResponseEntity<?> buscarTodasApostas() {
        return null;
    }

    private void isUsuarioExistente(String cpf) {
        User user = userRepository.findByCpf(cpf);

        if (user != null){
            log.info("Usuário existe, seguindo com o fluxo...");
        } else {
            throw new UserNotFoundException("Usuário não encontrado!");
        }
    }

    private void isApostaNova(HashSet<Integer> numbers, String cpf) {
        User user = userRepository.findByCpf(cpf);
        log.info("Procurando pelos números...");


        log.info("Numbers: " + numbers);

        // TODO: Está permitindo duplicar porque a coluna tá sendo criada como binário, tentar com String a lista de numeros
//        HashSet<Bet> betSet = betRepository.getBetByNumbers(numbers, cpf);
//        HashSet<Bet> betSet = betRepository.getBetByNumbersByte(numbers, cpf);
        HashSet<Bet> betSet = betRepository.getBetByNumbersString(numbers.toString(), cpf);

        log.info("Encontrou ou não a aposta de acordo com os números...");
        log.info("IsEmpty? : " + String.valueOf(betSet.isEmpty()));

        if (!betSet.isEmpty()){
            throw new BetAlreadyExistsException("Aposta já existe para o usuário: " + cpf);
        }

    }
}

package br.com.training.service.bet;

import br.com.training.dto.bet.BetRequestDto;
import br.com.training.exception.bet.BetAlreadyExistsException;
import br.com.training.exception.bet.BetNotFoundException;
import br.com.training.exception.user.UserNotFoundException;
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

import java.util.ArrayList;
import java.util.Collections;
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
        Collections.sort(betRequestDto.getNumbers());
        isApostaNova(betRequestDto.getNumbers(), betRequestDto.getCpf());

        Bet bet = new Bet(betRequestDto.getNumbers().toString(), betRequestDto.getCreatedAt());

        betRepository.save(bet);
        betPerUserRepository.save(new BetPerUser(userRepository.findByCpf(betRequestDto.getCpf()), bet));

        log.info("Aposta salva no banco de dados com êxito.");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deletarAposta(Long id) {
        log.info("Deletando a aposta de ID: " + id);

        Bet bet = betRepository.findById(id).orElseThrow(() -> new BetNotFoundException("Aposta não encontrada!"));
        betPerUserRepository.deleteByBet(bet.getBetId());
        betRepository.deleteById(bet.getBetId());

        log.info("A seguinte aposta foi deletada do banco de dados: " + bet);

        return new ResponseEntity<>(null, HttpStatus.OK);
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

    private void isApostaNova(ArrayList<Integer> numbers, String cpf) {
//        User user = userRepository.findByCpf(cpf);
        log.info("Procurando pelos números...");
        log.info("Numbers: " + numbers);

        HashSet<Bet> betSet = betRepository.getBetByNumbersString(numbers.toString(), cpf);

        log.info("Encontrou ou não a aposta de acordo com os números...");
        log.info("Foi encontrado alguma aposta? : " + String.valueOf(betSet.isEmpty()));

        if (!betSet.isEmpty()){
            throw new BetAlreadyExistsException("Aposta já existe para o usuário: " + cpf);
        }

    }
}

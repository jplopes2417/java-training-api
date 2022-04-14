package br.com.training.service.bet;

import br.com.training.dto.bet.BetRequestDto;
import br.com.training.exception.bet.BetAlreadyExistsException;
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
import java.util.Optional;

@Slf4j
@Service
public class BetServiceImpl implements BetService {

    // TODO: Implementar a lógica dos serviços de aposta

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

//        betRepository.save(bet);
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

    private void isApostaNova(ArrayList<Integer> numbers, String cpf) {
        User user = userRepository.findByCpf(cpf);
        Optional<Bet> betOptional = betRepository.getBetByNumbers(numbers);
        // TODO: Procurar na BetPerUserRepository se a aposta que retornou é daquele usuário pelo ID da Aposta
        Optional<BetPerUser> betPerUser = betPerUserRepository.findByUserAndBet(user.getId(), betOptional.get().getBetId());

        if (betPerUser.isEmpty()){
            log.info("Não foi encontrada aposta para o CPF: " + cpf);
        } else {
            log.error("Já existe uma aposta para o CPF: " + cpf);
            throw new BetAlreadyExistsException("Aposta já existe!");
        }
    }
}

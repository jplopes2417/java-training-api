package br.com.training.service.bet;

import br.com.training.dto.bet.BetRequestDto;
import org.springframework.http.ResponseEntity;

public interface BetService {

    //  cadastrar, buscar e remover apostas de usuários cadastrados.

    ResponseEntity<?> salvarAposta(BetRequestDto user);
    ResponseEntity<?> deletarAposta(Long id);
    ResponseEntity<?> buscarAposta(String cpf);
    ResponseEntity<?> buscarTodasApostas();

}

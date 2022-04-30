package br.com.training.service.bet;

import br.com.training.dto.bet.BetRequestDto;
import br.com.training.dto.bet.BetResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface BetService {

    ResponseEntity<HttpStatus> salvarAposta(String betName, BetRequestDto user);
    ResponseEntity<HttpStatus> deletarAposta(Long id);
    ResponseEntity<BetResponseDto> buscarAposta(Long id);
    ResponseEntity<Set<BetResponseDto>> buscarTodasApostas();

}

package br.com.training.service.bet;


import br.com.training.dto.bet.BetConfigurationRequestDto;
import br.com.training.dto.bet.BetConfigurationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface BetConfigurationService {

    ResponseEntity<HttpStatus> salvarNovaConfiguracaoAposta(BetConfigurationRequestDto betConfigurationRequestDto);
    ResponseEntity<HttpStatus> deletarConfiguracaoAposta(Long id);
    ResponseEntity<BetConfigurationResponseDto> buscarConfiguracaoAposta(Long id);
    ResponseEntity<Set<BetConfigurationResponseDto>> buscarTodasConfiguracoesApostas();

}

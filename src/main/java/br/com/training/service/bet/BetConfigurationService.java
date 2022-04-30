package br.com.training.service.bet;


import br.com.training.dto.bet.BetConfigurationRequestDto;
import br.com.training.dto.bet.BetConfigurationResponseDto;
import br.com.training.dto.bet.BetConfigurationUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface BetConfigurationService {

    void salvarNovaConfiguracaoAposta(BetConfigurationRequestDto betConfigurationRequestDto);
    void deletarConfiguracaoAposta(String id);
    BetConfigurationResponseDto buscarConfiguracaoAposta(String betName);
    Set<BetConfigurationResponseDto> buscarTodasConfiguracoesApostas();
    void atualizarConfiguracaoAposta(String id, BetConfigurationUpdateDto betConfigurationUpdateDto);

}

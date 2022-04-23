package br.com.training.service.bet;

import br.com.training.dto.bet.BetConfigurationRequestDto;
import br.com.training.dto.bet.BetConfigurationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public class BetConfigurationImpl implements BetConfigurationService{

    @Override
    public ResponseEntity<HttpStatus> salvarNovaConfiguracaoAposta(BetConfigurationRequestDto betConfigurationRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deletarConfiguracaoAposta(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<BetConfigurationResponseDto> buscarConfiguracaoAposta(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Set<BetConfigurationResponseDto>> buscarTodasConfiguracoesApostas() {
        return null;
    }
}

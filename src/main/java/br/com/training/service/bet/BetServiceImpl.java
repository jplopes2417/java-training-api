package br.com.training.service.bet;

import br.com.training.dto.bet.BetRequestDto;
import org.springframework.http.ResponseEntity;

public class BetServiceImpl implements BetService {

    // TODO: Implementar a lógica dos serviços de aposta

    @Override
    public ResponseEntity<?> salvarAposta(BetRequestDto user) {
        return null;
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
}

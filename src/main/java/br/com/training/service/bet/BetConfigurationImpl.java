package br.com.training.service.bet;

import br.com.training.dto.bet.BetConfigurationRequestDto;
import br.com.training.dto.bet.BetConfigurationResponseDto;
import br.com.training.exception.bet.BetConfigurationNotFoundException;
import br.com.training.mapper.bet.BetConfigurationMapper;
import br.com.training.model.bet.BetConfiguration;
import br.com.training.model.bet.BetType;
import br.com.training.repository.bet.BetConfigurationRepository;
import br.com.training.repository.bet.BetTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
public class BetConfigurationImpl implements BetConfigurationService {

    private final BetConfigurationRepository betConfigurationRepository;

    private final BetTypeRepository betTypeRepository;

    public BetConfigurationImpl(BetConfigurationRepository betConfigurationRepository, BetTypeRepository betTypeRepository) {
        this.betConfigurationRepository = betConfigurationRepository;
        this.betTypeRepository = betTypeRepository;
    }

    @Override
    public void salvarNovaConfiguracaoAposta(BetConfigurationRequestDto betConfigurationRequestDto) {
        log.info("Entrando com a nova configuração: " + betConfigurationRequestDto);
        BetConfiguration betConfiguration = BetConfigurationMapper.INSTANCE.toModel(betConfigurationRequestDto);

        betConfiguration.setCreatedAt(LocalDateTime.now());
        betConfigurationRepository.save(betConfiguration);

        // TODO: Estudar porque quando inicializo um objeto, o hibernate não permite salvar no banco por causa do ID

//        BetType betType = BetType
//                .builder()
//                .betConfiguration(betConfiguration)
//                .betName(betConfigurationRequestDto.getBetName())
//                .build();

//        betTypeRepository.save(betType);
        betTypeRepository.save(new BetType(betConfigurationRequestDto.getBetName(), betConfiguration));
        log.info("Configuração salva no banco!");
    }

    @Override
    public void deletarConfiguracaoAposta(String id) {
        log.info("Deletando a configuração com o seguinte ID: " + id);
        BetConfiguration betConfiguration = betConfigurationRepository.findById(id).orElseThrow(() -> new BetConfigurationNotFoundException("Bet Config not found."));
        BetType betType = betTypeRepository.findByBetConfiguration(betConfiguration);
        betTypeRepository.deleteById(betType.getBetTypeId());
        betConfigurationRepository.deleteById(betConfiguration.getKey());
        log.info("Aposta deletada.");
    }

    @Override
    public BetConfigurationResponseDto buscarConfiguracaoAposta(String id) {
        return null;
    }

    @Override
    public Set<BetConfigurationResponseDto> buscarTodasConfiguracoesApostas() {
        return null;
    }
}

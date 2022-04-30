package br.com.training.service.bet;

import br.com.training.dto.bet.BetConfigurationRequestDto;
import br.com.training.dto.bet.BetConfigurationResponseDto;
import br.com.training.dto.bet.BetConfigurationUpdateDto;
import br.com.training.exception.bet.BetConfigurationAlreadyExistsException;
import br.com.training.exception.bet.BetConfigurationNotFoundException;
import br.com.training.mapper.bet.BetConfigurationMapper;
import br.com.training.model.bet.BetConfiguration;
import br.com.training.model.bet.BetType;
import br.com.training.repository.bet.BetConfigurationRepository;
import br.com.training.repository.bet.BetTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BetConfigurationServiceImpl implements BetConfigurationService {

    public static final String BET_CONFIG_NOT_FOUND = "Bet Config not found.";
    public static final String PARAMETRO_JA_EXISTE = "Parametro ja existe!";
    private final BetConfigurationRepository betConfigurationRepository;
    private final BetTypeRepository betTypeRepository;

    public BetConfigurationServiceImpl(BetConfigurationRepository betConfigurationRepository, BetTypeRepository betTypeRepository) {
        this.betConfigurationRepository = betConfigurationRepository;
        this.betTypeRepository = betTypeRepository;
    }

    @Override
    public void salvarNovaConfiguracaoAposta(BetConfigurationRequestDto betConfigurationRequestDto) {
        log.info("Entrando com a nova configuração: " + betConfigurationRequestDto);
        BetConfiguration betConfiguration = BetConfigurationMapper.INSTANCE.toModel(betConfigurationRequestDto);

        boolean validarExistenciaConfiguracao = validarExistenciaConfiguracao(betConfigurationRequestDto, betConfiguration);
        if (validarExistenciaConfiguracao) throw new BetConfigurationAlreadyExistsException(PARAMETRO_JA_EXISTE);

        betConfiguration.setCreatedAt(LocalDateTime.now());
        betConfigurationRepository.save(betConfiguration);

        betTypeRepository.save(new BetType(betConfigurationRequestDto.getBetName(), betConfiguration));
        log.info("Configuração salva no banco!");
    }

    @Override
    public void deletarConfiguracaoAposta(String id) {
        log.info("Deletando a configuração com o seguinte ID: " + id);

        BetConfiguration betConfiguration = betConfigurationRepository
                .findById(id)
                .orElseThrow(() -> new BetConfigurationNotFoundException(BET_CONFIG_NOT_FOUND));

        ArrayList<BetType> byBetConfiguration = betTypeRepository.findByBetConfiguration(betConfiguration);

        byBetConfiguration.forEach(betType -> betTypeRepository.deleteById(betType.getBetTypeId()));

        betConfigurationRepository.deleteById(betConfiguration.getKey());
        log.info("Aposta deletada.");
    }

    @Override
    public BetConfigurationResponseDto buscarConfiguracaoAposta(String betName) {
        log.info("Buscando as configurações da aposta: " + betName);
        ArrayList<BetType> byBetConfiguration = betTypeRepository.findByBetName(betName);

        if (byBetConfiguration.isEmpty()) {
            throw new BetConfigurationNotFoundException(BET_CONFIG_NOT_FOUND);
        }

        BetConfiguration betConfiguration = byBetConfiguration.stream().distinct().findFirst().get().getBetConfiguration();
        BetConfigurationResponseDto betConfigurationResponseDto = BetConfigurationMapper.INSTANCE.toResponseFromModel(betConfiguration);
        HashMap<String, Integer> hashMap = new HashMap<>();

        byBetConfiguration.forEach(betType -> {
            betConfigurationResponseDto.setBetName(betType.getBetName());
            hashMap.put(betType.getBetConfiguration().getKey(), betType.getBetConfiguration().getValue());
        });
        betConfigurationResponseDto.setParams(hashMap);

        return betConfigurationResponseDto;
    }

    @Override
    public Set<BetConfigurationResponseDto> buscarTodasConfiguracoesApostas() {
        return null;
    }

    @Override
    public void atualizarConfiguracaoAposta(String id, BetConfigurationUpdateDto betConfigurationUpdateDto) {
        log.info("Procurando pelo seguinte parametro no banco de dados: " + id);

        Optional<BetConfiguration> betConfiguration = Optional.ofNullable(betConfigurationRepository
                .findById(id)
                .orElseThrow(() -> new BetConfigurationNotFoundException(BET_CONFIG_NOT_FOUND)));

        ArrayList<BetType> betTypeArrayList = betTypeRepository.findByBetConfiguration(betConfiguration.get());
        if(betTypeArrayList.stream().allMatch(Objects::isNull)) throw new BetConfigurationNotFoundException(BET_CONFIG_NOT_FOUND);
        BetType betType = new BetType();

        betTypeArrayList.forEach(betTypeArray -> {
            betType.setBetTypeId(betTypeArray.getBetTypeId());
            betType.setBetName(betConfigurationUpdateDto.getBetName());
            betType.setBetConfiguration(betConfiguration.get());
        });

        betConfiguration.get().setUpdatedAt(LocalDateTime.now());
        betConfiguration.get().setValue(betConfigurationUpdateDto.getValue());

        betConfigurationRepository.save(betConfiguration.get());
        betTypeRepository.save(betType);

    }

    private boolean validarExistenciaConfiguracao(BetConfigurationRequestDto betConfigurationRequestDto, BetConfiguration betConfiguration) {
        Optional<BetConfiguration> configuration = betConfigurationRepository.findById(betConfiguration.getKey());
        Optional<BetType> betType = betTypeRepository.findByBetNameAndBetConfiguration(betConfigurationRequestDto.getBetName(), configuration);

        return configuration.filter(value -> betConfiguration.getKey().equalsIgnoreCase(value.getKey())
                && betConfigurationRequestDto.getBetName().equalsIgnoreCase(betType.get().getBetName())).isPresent();
    }
}

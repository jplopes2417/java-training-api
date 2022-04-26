package br.com.training.mapper.bet;

import br.com.training.dto.bet.BetConfigurationRequestDto;
import br.com.training.dto.bet.BetConfigurationResponseDto;
import br.com.training.model.bet.BetConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BetConfigurationMapper {

    BetConfigurationMapper INSTANCE = Mappers.getMapper(BetConfigurationMapper.class);
    BetConfiguration toModel(BetConfigurationRequestDto betConfigurationRequestDto);
    BetConfiguration toModelFromResponse(BetConfigurationResponseDto betConfigurationResponseDto);
    BetConfigurationResponseDto toResponseFromModel(BetConfiguration betConfiguration);

}

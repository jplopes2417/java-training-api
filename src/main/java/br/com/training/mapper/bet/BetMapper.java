package br.com.training.mapper.bet;

import br.com.training.dto.bet.BetRequestDto;
import br.com.training.mapper.user.UserMapper;
import br.com.training.model.Bet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BetMapper {

    BetMapper INSTANCE = Mappers.getMapper(BetMapper.class);

    Bet toModel(BetRequestDto betRequestDto);
    BetRequestDto toBetRequestDto(Bet bet);

}

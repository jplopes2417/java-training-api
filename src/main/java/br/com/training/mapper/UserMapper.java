package br.com.training.mapper;

import br.com.training.dto.request.UserRequestDto;
import br.com.training.dto.response.UserResponseDto;
import br.com.training.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserRequestDto dto);

    UserRequestDto toDto(User dto);

    UserResponseDto toResponseDto(UserRequestDto dto);

    UserResponseDto toModelFromUser(User user);

    UserRequestDto userDtoToUser(User user);

    User userToUserDto(UserRequestDto userDto);

}

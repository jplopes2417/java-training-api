package br.com.training.dto.user;

import br.com.training.dto.user.UserRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class UserRequestDtoTest {

    @Test
    void naoDeveriaSalvarUsuarioComCPFInvalido() {

        Validator validator;
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("TESTE")
                .email("teste@teste.algo")
                .cpf("111.983.050-23")
                .birthDate(LocalDate.now())
                .build();

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertFalse(violations.isEmpty());

    }

}
package br.com.training.dto;


import br.com.training.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String name;

    private String email;

    private LocalDate birthDate;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.birthDate = user.getBirthDate();
    }

    public static Page<UserResponseDto> converterPage(Page<User> topicos) {
        return topicos.map(UserResponseDto::new);
    }

}

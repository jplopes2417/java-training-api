package br.com.training.dto.bet;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BetRequestDto {

    @CPF
    @NotBlank(message = "CPF não estar vazio")
    private String cpf;

    @UniqueElements
    @NotEmpty(message = "Os números da aposta não podem estar em branco")
    private ArrayList<Integer> numbers;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "A data de criação não pode estar em branco")
    private LocalDateTime createdAt;

}

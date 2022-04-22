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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class BetResponseDto {

    private String cpf;

    private ArrayList<Integer> numbers;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
}

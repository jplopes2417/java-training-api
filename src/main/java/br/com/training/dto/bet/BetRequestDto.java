package br.com.training.dto.bet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BetRequestDto {

    @UniqueElements
    @NotBlank(message = "Os números da aposta não podem estar em branco")
    private ArrayList<Integer> numbers;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "A data de criação não pode estar em branco")
    private LocalDateTime createdAt;

}

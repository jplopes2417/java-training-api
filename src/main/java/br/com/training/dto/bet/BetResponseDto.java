package br.com.training.dto.bet;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class BetResponseDto {

    private String cpf;

    private ArrayList<Integer> numbers;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
}

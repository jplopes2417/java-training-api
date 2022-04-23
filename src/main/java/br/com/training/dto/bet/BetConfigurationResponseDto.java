package br.com.training.dto.bet;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class BetConfigurationResponseDto {

    private String betName;

    private String key;

    private int value;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

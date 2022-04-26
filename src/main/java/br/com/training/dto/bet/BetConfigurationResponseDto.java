package br.com.training.dto.bet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class BetConfigurationResponseDto {

    private String betName;

//    private ArrayList<BetConfigurationRequestDto> betConfigurationRequestDtos;
    private HashMap<String, Integer> params;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

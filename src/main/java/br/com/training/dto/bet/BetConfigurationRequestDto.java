package br.com.training.dto.bet;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class BetConfigurationRequestDto {

    @NotBlank(message = "Identificador da aposta precisa ser informado!")
    private String key;

    @NotBlank(message = "Valor precisa ser informado!")
    private int value;

}

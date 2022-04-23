package br.com.training.dto.bet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BetConfigurationRequestDto {

    @NotBlank(message = "Identificador da aposta precisa ser informado!")
    private String key;

    @NotBlank(message = "Valor precisa ser informado!")
    private int value;

}

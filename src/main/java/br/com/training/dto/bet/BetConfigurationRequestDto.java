package br.com.training.dto.bet;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class BetConfigurationRequestDto {

    @NotBlank(message = "O nome da aposta é obrigatório")
    @Length(min = 3, max = 50, message = "O nome deverá ter no máximo {max} caracteres")
    private String betName;

    @NotBlank(message = "Identificador da aposta precisa ser informado!")
    @Length(min = 3, max = 50, message = "O nome do identificador da aposta deverá ter no máximo {max} caracteres")
    private String key;

    @NotNull
    @Min(0)
    private int value;

}

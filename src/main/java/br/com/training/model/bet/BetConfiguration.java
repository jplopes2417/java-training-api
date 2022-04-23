package br.com.training.model.bet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "bet_configuration")
public class BetConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private String key;

    @Column
    @NotBlank
    private int value;

    @Column(name = "dt_creation")
    @NotBlank
    private LocalDateTime createdAt;

    @Column(name = "dt_update")
    private LocalDateTime updatedAt;

}

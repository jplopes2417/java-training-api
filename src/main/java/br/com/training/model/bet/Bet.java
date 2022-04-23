package br.com.training.model.bet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "bet")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bet_id")
    private Long betId;

    @Column(name = "numbers", nullable = false)
    private String numbers;

    @Column(name = "dt_creation", nullable = false)
    private LocalDateTime createdAt;

    public Bet(String numbers, LocalDateTime createdAt) {
        this.numbers = numbers;
        this.createdAt = createdAt;
    }
}

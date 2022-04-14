package br.com.training.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

//    @ManyToOne()
//    @JoinColumn(name = "cpf")
//    private User user;

    @Column(name = "numbers", nullable = false)
    private ArrayList<Integer> numbers;

    @Column(name = "dt_creation", nullable = false)
    private LocalDateTime createdAt;
}

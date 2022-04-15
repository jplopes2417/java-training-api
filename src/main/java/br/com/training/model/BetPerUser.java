package br.com.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bet_user")
public class BetPerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bet_per_user_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bet_id", nullable = false)
    private Bet bet;

    public BetPerUser(User user, Bet bet) {
        this.user = user;
        this.bet = bet;
    }
}

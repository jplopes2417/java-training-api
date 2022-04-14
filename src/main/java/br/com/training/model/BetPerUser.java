package br.com.training.model;

import javax.persistence.*;

@Entity
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

}

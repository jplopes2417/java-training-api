package br.com.training.repository.bet;

import br.com.training.model.BetPerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetPerUserRepository extends JpaRepository<BetPerUser, Long> {
}

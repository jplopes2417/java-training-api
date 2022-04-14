package br.com.training.repository.bet;

import br.com.training.model.BetPerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BetPerUserRepository extends JpaRepository<BetPerUser, Long> {

    Optional<BetPerUser> findByUserAndBet(Long userId, Long betId);
}

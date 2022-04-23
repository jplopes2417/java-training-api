package br.com.training.repository.bet;

import br.com.training.model.bet.BetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetTypeRepository extends JpaRepository<BetType, Long> {

}

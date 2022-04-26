package br.com.training.repository.bet;

import br.com.training.model.bet.BetConfiguration;
import br.com.training.model.bet.BetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BetTypeRepository extends JpaRepository<BetType, Long> {
    ArrayList<BetType> findByBetConfiguration(BetConfiguration betConfiguration);

    ArrayList<BetType> findByBetName(String betName);
}

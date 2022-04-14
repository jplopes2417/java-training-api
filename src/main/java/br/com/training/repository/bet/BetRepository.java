package br.com.training.repository.bet;

import br.com.training.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    // TODO: Pensar numa estratégica melhor de fazer isso, pois está dando erro na hora de subir o Spring
    Optional<Bet> getBetByNumbers(ArrayList<Integer> numbers);

}

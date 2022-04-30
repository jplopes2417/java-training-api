package br.com.training.repository.bet;

import br.com.training.model.bet.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    @Query(value = "SELECT * FROM bet B WHERE B.NUMBERS IN (?1) " +
            "AND EXISTS ( SELECT 1 " +
            "FROM bet_user BU WHERE BU.BET_ID = B.BET_ID " +
            "AND BU.USER_ID = (SELECT U.USER_ID FROM user U WHERE U.CPF = ?2))",
            nativeQuery = true)
    HashSet<Bet> getBetByNumbersString(String numbers, String cpf);

}

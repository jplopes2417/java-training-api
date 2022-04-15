package br.com.training.repository.bet;

import br.com.training.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    // Importante: Colocar o In no final das consultas para buscar listas de dados (tipo o IN do bd)
    // solução tirada de: https://stackoverflow.com/questions/60397201/spring-jpa-repository-operator-simple-property-on-jsonobject-requires-a-scalar
//    @Query(value = "SELECT * FROM BET B WHERE B.NUMBERS IN (?1)", nativeQuery = true)
    @Query(value = "SELECT * FROM BET B WHERE B.NUMBERS IN (?1) " +
            "AND NOT EXISTS ( SELECT 1 " +
                            "FROM BET_USER BU WHERE BU.BET_ID = B.BET_ID " +
                            "AND BU.USER_ID = (SELECT U.CPF FROM USER U WHERE U.CPF = ?2))",
            nativeQuery = true)

    HashSet<Bet> getBetByNumbers(HashSet<Integer> numbers, String cpf);


}

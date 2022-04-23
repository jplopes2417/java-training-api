package br.com.training.model.bet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bet_type")
public class BetType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bet_type_id", nullable = false)
    private Long betTypeId;

    @Column(name = "bet_type", nullable = false)
    private String betName;

    @ManyToOne
    @Column(name = "bet_key")
    @JoinColumn(name = "key", nullable = false)
    private Long betKeyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BetType betType = (BetType) o;
        return betTypeId != null && Objects.equals(betTypeId, betType.betTypeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

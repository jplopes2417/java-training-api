package br.com.training.model.bet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "bet_configuration")
public class BetConfiguration {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @Column(name = "key_id")
    private String key;

    @Column
    private int value;

    @Column(name = "dt_creation")
    private LocalDateTime createdAt;

    @Column(name = "dt_update")
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BetConfiguration that = (BetConfiguration) o;
        return key != null && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

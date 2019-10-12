package ru.malifaux.programming.FTBWL.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import ru.malifaux.programming.FTBWL.common.things.entity.Identifiable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "game_system")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "game_system_value_uindex", columnNames = "value"),
        @UniqueConstraint(name = "game_system_code_uindex", columnNames = "code")})
public class GameSystemEntity extends Identifiable {

    /** Значение*/
    @NotBlank
    @NaturalId(mutable = true)
    private String value;

    /** Код*/
    @NotBlank
    @NaturalId(mutable = true)
    private String code;
}

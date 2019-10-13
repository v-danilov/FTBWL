package ru.malifaux.programming.FTBWL.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import ru.malifaux.programming.FTBWL.common.things.entity.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rule_pack")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "rule_pack_code_uindex", columnNames = "code")})
public class RulepackEntity extends Identifiable {

    /** Значение*/
    @NotBlank
    private String value;

    /** Код*/
    @NotBlank
    @NaturalId(mutable = true)
    private String code;

    /** Система*/
    @ManyToOne
    @NotNull
    @JoinColumn(name = "game_system_id")
    private GameSystemEntity gameSystem;
}

package ru.malifaux.programming.FTBWL.dictionary.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.malifaux.programming.FTBWL.common.things.entity.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "faction")
public class FactionEntity extends Identifiable {

    @NotBlank
    @Column(name = "system_name")
    private String systemName;

    private String image;

    private String name;

    /**
     * Система
     */
    @ManyToOne
    @NotNull
    @JoinColumn(name = "game_system_id")
    private GameSystemEntity gameSystem;
}

package ru.malifaux.programming.FTBWL.event.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDto implements DTO {

    private String id;

    /** Имя пользователя для отображения*/
    @NotBlank
    private String fullName;

    /** Эвент, в котором участвует*/
    private String eventId;

    /** Турнирные очки*/
    private int tp;

    /** Разница в очках с оппонентом*/
    private int diff;

    /** Суммарное количество очков в играх*/
    private int vp;

    /** Количество игр на эвенте*/
    private int games;

    /** Место в таблице с результатами*/
    private int pos;

    /** Проксибот */
    private boolean ringer;

    /** Покинул турнир */
    private boolean leaved;
}

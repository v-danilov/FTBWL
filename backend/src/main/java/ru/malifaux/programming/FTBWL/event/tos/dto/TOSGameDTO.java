package ru.malifaux.programming.FTBWL.event.tos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malifaux.programming.FTBWL.event.dto.GameDTO;

@Setter
@Getter
@NoArgsConstructor
public class TOSGameDTO extends GameDTO {

    /** Определение атакующего игрока*/
    private int attacker_num;
}

package ru.malifaux.programming.FTBWL.event.tos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malifaux.programming.FTBWL.event.dto.GameDto;

@Setter
@Getter
@NoArgsConstructor
public class TOSGameDto extends GameDto {

    /** Определение атакующего игрока*/
    private int attacker_num;
}

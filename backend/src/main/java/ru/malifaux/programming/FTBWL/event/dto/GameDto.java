package ru.malifaux.programming.FTBWL.event.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameDto implements DTO {

    private String id;

    /** Номер стола */
    private int tableNum;

    /** Эвент игры */
    private String eventId;

    /** Первый игрок*/
    private PlayerDto player1;

    /** Турнирные очки первого игрока*/
    private int player1_tp;

    /** Победные очки первого игрока*/
    private int player1_vp;

    /** Разница в очках первого игрока*/
    private int player1_diff;

    /** Первый игрок*/
    private PlayerDto player2;

    /** Турнирные очки первого игрока*/
    private int player2_tp;

    /** Победные очки первого игрока*/
    private int player2_vp;

    /** Разница в очках первого игрока*/
    private int player2_diff;

    /** Бай*/
    private boolean isBye;

    /** Жульничество*/
    private boolean isForfeit;

}

package ru.malifaux.programming.FTBWL.event.tos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malifaux.programming.FTBWL.dictionary.dto.FactionDTO;
import ru.malifaux.programming.FTBWL.event.dto.PlayerDTO;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TOSPlayerDTO extends PlayerDTO {

    /** Основная фракция игррока*/
    @NotNull
    private FactionDTO allegiance;

    /** Союз*/
    private FactionDTO envoy;

    /** Гарнизон*/
    private GarrisonDTO garrisonDTO;
}

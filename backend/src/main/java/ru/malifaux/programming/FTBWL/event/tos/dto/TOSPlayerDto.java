package ru.malifaux.programming.FTBWL.event.tos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malifaux.programming.FTBWL.dictionary.dto.FactionDto;
import ru.malifaux.programming.FTBWL.event.dto.PlayerDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TOSPlayerDto extends PlayerDto {

    /** Основная фракция игррока*/
    @NotNull
    private FactionDto allegiance;

    /** Союз*/
    private FactionDto envoy;

    /** Гарнизон*/
    private GarrisonDto garrisonDTO;
}

package ru.malifaux.programming.FTBWL.event.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malifaux.programming.FTBWL.common.gateway.dto.UserDTO;
import ru.malifaux.programming.FTBWL.common.locations.dto.PlaceDTO;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventFormatDTO;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventStatusDTO;
import ru.malifaux.programming.FTBWL.dictionary.dto.GameSystemDTO;
import ru.malifaux.programming.FTBWL.dictionary.dto.RulepackDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventInfoDTO implements DTO {

    private String id;

    /** event name*/
    private String name;
    /** event status*/
    private EventStatusDTO status;
    /** event organiser*/
    private UserDTO organiser;
    /** event location*/
    private PlaceDTO place;
    /** game system of event*/
    private GameSystemDTO gameType;
    /** event rule pack*/
    private RulepackDTO rulepack;
    /** event format*/
    private EventFormatDTO format;
    /** event start date*/
    private LocalDateTime timeStart;
    /** event end date*/
    private LocalDateTime timeEnd;
    /** Cost of participation on event*/
    private BigDecimal price;
    /**
     * event players
     **/
    private List<PlayerDTO> players;

}

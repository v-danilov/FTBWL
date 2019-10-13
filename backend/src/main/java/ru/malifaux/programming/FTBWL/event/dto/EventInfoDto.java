package ru.malifaux.programming.FTBWL.event.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malifaux.programming.FTBWL.common.gateway.dto.UserDto;
import ru.malifaux.programming.FTBWL.common.locations.dto.LocationDto;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventFormatDto;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventStatusDto;
import ru.malifaux.programming.FTBWL.dictionary.dto.GameSystemDto;
import ru.malifaux.programming.FTBWL.dictionary.dto.RulepackDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventInfoDto implements DTO {

    private String id;

    /** event name*/
    private String name;
    /** event status*/
    private EventStatusDto status;
    /** event organiser*/
    private UserDto organiser;
    /** event location*/
    private LocationDto location;
    /** game system of event*/
    private GameSystemDto gameType;
    /** event rule pack*/
    private RulepackDto rulepack;
    /** event format*/
    private EventFormatDto format;
    /** event start date*/
    private LocalDateTime eventStart;
    /** event end date*/
    private LocalDateTime eventEnd;
    /** Cost of participation on event*/
    private BigDecimal price;

}

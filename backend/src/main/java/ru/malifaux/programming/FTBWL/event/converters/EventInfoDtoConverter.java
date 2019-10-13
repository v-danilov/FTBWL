package ru.malifaux.programming.FTBWL.event.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.malifaux.programming.FTBWL.common.gateway.converters.UserDtoConverter;
import ru.malifaux.programming.FTBWL.common.gateway.dto.UserDto;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventStatusDto;
import ru.malifaux.programming.FTBWL.event.dto.EventInfoDto;
import ru.malifaux.programming.FTBWL.event.entity.EventEntity;

@Component
@RequiredArgsConstructor
public class EventInfoDtoConverter {

    private final EventStatusDtoConverter eventStatusDtoConverter;
    private final UserDtoConverter userDtoConverter;

    public EventInfoDto convertToDto(EventEntity entity){
        EventInfoDto eventInfoDto = new EventInfoDto();
        eventInfoDto.setId(entity.getId().toString());
        eventInfoDto.setName(entity.getName());

        EventStatusDto eventStatusDto = eventStatusDtoConverter.convertToDto(entity.getStatus());
        eventInfoDto.setStatus(eventStatusDto);

        UserDto userDto = userDtoConverter.convertToDto(entity.getOrganiser());
        eventInfoDto.setOrganiser(userDto);



        return eventInfoDto;
    }
}

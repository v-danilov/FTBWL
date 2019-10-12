package ru.malifaux.programming.FTBWL.dictionary;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventStatusDto;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventStatusEntity;

@Component
@RequiredArgsConstructor
public class EventStatusDtoConverter {

    public EventStatusDto convertToDto(@NotNull EventStatusEntity entity){
        EventStatusDto eventStatusDto = new EventStatusDto();
        eventStatusDto.setId(entity.getId().toString());
        eventStatusDto.setCode(entity.getCode());
        eventStatusDto.setValue(entity.getValue());
        return eventStatusDto;
    }
}

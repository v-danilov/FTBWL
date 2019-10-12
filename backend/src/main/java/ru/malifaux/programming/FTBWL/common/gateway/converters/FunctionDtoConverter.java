package ru.malifaux.programming.FTBWL.common.gateway.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.malifaux.programming.FTBWL.common.gateway.dto.FunctionDto;
import ru.malifaux.programming.FTBWL.common.gateway.entity.FunctionEntity;

@Component
@RequiredArgsConstructor
public class FunctionDtoConverter {

    public FunctionDto convertToDto (FunctionEntity functionEntity){
        FunctionDto dto = new FunctionDto();
        dto.setId(functionEntity.getId().toString());
        dto.setCode(functionEntity.getCode());
        dto.setValue(functionEntity.getValue());
        return dto;
    }

}

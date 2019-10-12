package ru.malifaux.programming.FTBWL.common.gateway.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.common.gateway.dto.FunctionDto;
import ru.malifaux.programming.FTBWL.common.gateway.dto.RoleDto;
import ru.malifaux.programming.FTBWL.common.gateway.entity.FunctionEntity;
import ru.malifaux.programming.FTBWL.common.gateway.entity.RoleEntity;
import ru.malifaux.programming.FTBWL.common.gateway.repository.FunctionRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleDtoConverter {

    private final FunctionDtoConverter functionDtoConverter;
    private final FunctionRepository functionRepository;

    public RoleDto convertToDto(RoleEntity entity){
        return convertToDto(entity, new RoleDto());
    }

    public RoleDto convertToDto(RoleEntity entity, RoleDto dto){
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());

        Set<FunctionDto> functions = entity.getFunctions().stream()
                .map(functionDtoConverter::convertToDto)
                .collect(Collectors.toSet());

        dto.setFunctions(functions);

        return dto;
    }

    public RoleEntity convertToEntity(RoleDto dto, RoleEntity entity){
        entity.setName(dto.getName());
        Set<FunctionEntity> functions = Optional.ofNullable(dto.getFunctions()).orElse(Collections.emptySet()).stream()
                .map(FunctionDto::getId).map(e -> functionRepository.findById(e).orElse(new FunctionEntity())).collect(Collectors.toSet());
        entity.setFunctions(functions);
        return entity;
    }
}

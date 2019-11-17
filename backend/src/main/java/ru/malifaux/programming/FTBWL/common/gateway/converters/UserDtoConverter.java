package ru.malifaux.programming.FTBWL.common.gateway.converters;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.common.gateway.dto.RoleDTO;
import ru.malifaux.programming.FTBWL.common.gateway.dto.UserDTO;
import ru.malifaux.programming.FTBWL.common.gateway.entity.UserEntity;
import ru.malifaux.programming.FTBWL.event.converters.OricaMapper;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDtoConverter {

    public UserDTO convertToDto(UserEntity entity) {
        MapperFactory customFactory = OricaMapper.getCustomFactory();
        customFactory.classMap(UserEntity.class, UserDTO.class).exclude("city").exclude("roles").byDefault().register();
        MapperFacade mapper = customFactory.getMapperFacade();

        UserDTO userDTO = mapper.map(entity, UserDTO.class);
        Set<RoleDTO> rolesDTO = OricaMapper.mapSetByDefault(entity.getRoles(), RoleDTO.class);
        userDTO.setRoles(rolesDTO);
        return userDTO;
    }

}

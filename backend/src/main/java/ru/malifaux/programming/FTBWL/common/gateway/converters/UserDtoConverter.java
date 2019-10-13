package ru.malifaux.programming.FTBWL.common.gateway.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.common.gateway.dto.UserDto;
import ru.malifaux.programming.FTBWL.common.gateway.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class UserDtoConverter {

    public UserDto convertToDto(UserEntity entity){
        UserDto userDto = new UserDto();
        return userDto;
    }
}

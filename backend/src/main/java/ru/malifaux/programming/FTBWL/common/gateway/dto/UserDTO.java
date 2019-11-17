package ru.malifaux.programming.FTBWL.common.gateway.dto;

import lombok.*;
import ru.malifaux.programming.FTBWL.common.locations.dto.CityDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private String id;

    /** Логин */
    @NotBlank
    private String username;

    /** Пароль */
    @NotBlank
    private String password;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Никнейм
     */
    private String nickname;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Полное имя
     */
    private String fullName;

    /** Код пользователя*/
    @NotBlank
    private String code;

    /** Адрес для спама */
    @NotNull
    @Email
    private String email;

    /**
     * Признак служебного пользователя
     */
    @NotNull
    private boolean service;

    /**
     * Город
     */
    private CityDTO city;

    /**
     * Роли пользователя
     */
    private Set<RoleDTO> roles;
}

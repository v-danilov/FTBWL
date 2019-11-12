package ru.malifaux.programming.FTBWL.common.gateway.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
public class RoleDTO {

    private String id;

    /** Название роли */
    @NotBlank
    @Size(max = 512)
    private String name;

    /** Функции, которые доступны для роли*/
    @Singular
    @Size(min = 1)
    private Set<FunctionDTO> functions;
}

package ru.malifaux.programming.FTBWL.common.gateway.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
public class FunctionDTO {

    private String id;

    /** Код функции */
    @NotBlank
    private String code;

    /** Описание */
    @NotBlank
    private String value;
}

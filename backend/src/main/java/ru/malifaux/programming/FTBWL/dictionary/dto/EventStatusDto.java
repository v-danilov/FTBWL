package ru.malifaux.programming.FTBWL.dictionary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventStatusDto {

    private String id;

    /** Значение*/
    private String value;
    /** Код*/
    private String code;
}

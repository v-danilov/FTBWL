package ru.malifaux.programming.FTBWL.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventFormatDto {

    private String id;

    /** Значение*/
    private String value;
    /** Код*/
    private String code;
}

package ru.malifaux.programming.FTBWL.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RulepackDto {

    private String id;

    /** Значение*/
    private String value;
    /** Код*/
    private String code;
}

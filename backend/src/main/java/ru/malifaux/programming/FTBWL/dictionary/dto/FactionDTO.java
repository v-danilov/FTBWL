package ru.malifaux.programming.FTBWL.dictionary.dto;

import lombok.Data;

@Data
public class FactionDTO {
    /**
     * Системеное имя фракци
     */
    private String systemName;
    /**
     * Имя фракции
     */
    private String name;
}

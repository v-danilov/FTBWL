package ru.malifaux.programming.FTBWL.common.locations.dto;

import lombok.Data;

@Data
public class PlaceDTO {
    private CityDTO city;
    private String name;
    private String street;
    private String building;
    private String coordinates;
}

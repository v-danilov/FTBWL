package ru.malifaux.programming.FTBWL.dictionary.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.malifaux.programming.FTBWL.common.things.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name = "place")
@Data
@EqualsAndHashCode(callSuper = true)
public class PlaceEntity extends Identifiable {

    @Column(name = "name")
    private String name;
    @Column(name = "coordinates")
    private String coordinates;
    @Column(name = "street")
    private String street;
    @Column(name = "building")
    private String building;
    @ManyToOne
    @JoinColumn(name = "city_id_old", referencedColumnName = "id", nullable = false)
    private CityEntity city;

}

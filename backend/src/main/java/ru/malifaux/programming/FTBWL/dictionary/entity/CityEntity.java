package ru.malifaux.programming.FTBWL.dictionary.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "city")
@Data
public class CityEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "city_id_seq")
    private int id;

    @Column(name = "name")
    private String name;
}

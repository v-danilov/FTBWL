package ru.malifaux.programming.FTBWL.event.services;

import ru.malifaux.programming.FTBWL.event.dto.DTO;

import java.util.List;

public interface BasicCRUDService <D extends DTO, E> {
    List<D> getAll();
    E getByID(String id);
    void save(E entity);
}

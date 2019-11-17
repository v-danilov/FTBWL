package ru.malifaux.programming.FTBWL.event.services;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;

import java.util.List;

public interface BasicCRUDService<E> {
    List<E> getAll(EntityGraph entityGraph);
    E getByID(String id, EntityGraph entityGraph);
    void save(E entity);
}

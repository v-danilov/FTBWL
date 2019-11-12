package ru.malifaux.programming.FTBWL.event.services;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import ru.malifaux.programming.FTBWL.event.dto.DTO;

import java.util.List;

public interface BasicCRUDService <D extends DTO, E> {
    List<E> getAll(EntityGraph entityGraph);

    E getByID(String id, EntityGraph entityGraph);
    void save(E entity);
}

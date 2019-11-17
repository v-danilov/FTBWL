package ru.malifaux.programming.FTBWL.event.services.event.impl;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.event.entity.EventEntity;
import ru.malifaux.programming.FTBWL.event.repository.EventRepository;
import ru.malifaux.programming.FTBWL.event.services.event.EventService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<EventEntity> getAll(EntityGraph eventEntityGraph) {
        List<EventEntity> eventEntities = new ArrayList<>();
        eventRepository.findAll(eventEntityGraph).forEach(eventEntities::add);
        return eventEntities;
    }

    @Override
    public EventEntity getByID(String id, EntityGraph eventEntityGraph) {
        return eventRepository.findById(UUID.fromString(id), eventEntityGraph).orElseThrow(NullPointerException::new);
    }

    @Override
    public void save(EventEntity entity) {
        eventRepository.save(entity);
    }
}

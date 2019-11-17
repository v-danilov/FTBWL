package ru.malifaux.programming.FTBWL.event.controllers;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.malifaux.programming.FTBWL.event.converters.OricaMapper;
import ru.malifaux.programming.FTBWL.event.dto.EventInfoDTO;
import ru.malifaux.programming.FTBWL.event.entity.EventEntity;
import ru.malifaux.programming.FTBWL.event.services.event.EventService;

import java.util.List;

@RestController
@RequestMapping("${api.version}")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/events")
    public List<EventInfoDTO> getAll() {
        EntityGraph eventEntityGraph = EntityGraphs.named(EventEntity.EventEntityGraph.EVENT_ENTITY_WITH_ORGANIZER);
        List<EventEntity> events = eventService.getAll(eventEntityGraph);
        return OricaMapper.mapListByDefault(events, EventInfoDTO.class);
    }

    @GetMapping("event/{id}")
    public EventInfoDTO findByID(@RequestParam String id) {
        EntityGraph eventEntityGraph = EntityGraphs.named(EventEntity.EventEntityGraph.EVENT_ENTITY_WITH_ORGANIZER);
        EventEntity event = eventService.getByID(id, eventEntityGraph);
        return OricaMapper.mapByDefault(event, EventInfoDTO.class);
    }

    @PostMapping
    public void saveEvent(@RequestParam EventEntity eventEntity) {
        eventService.save(eventEntity);
    }
}

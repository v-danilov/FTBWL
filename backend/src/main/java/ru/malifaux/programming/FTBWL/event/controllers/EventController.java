package ru.malifaux.programming.FTBWL.event.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.malifaux.programming.FTBWL.event.dto.EventInfoDto;
import ru.malifaux.programming.FTBWL.event.entity.EventEntity;
import ru.malifaux.programming.FTBWL.event.services.event.EventService;

import java.util.List;

@RestController
@RequestMapping ("${api.version}/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventInfoDto> getAll(){
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public EventEntity findByID(@RequestParam String id) {
        return eventService.getByID(id);
    }

    @PostMapping
    public void saveEvent(@RequestParam EventEntity eventEntity) {
        eventService.save(eventEntity);
    }
}

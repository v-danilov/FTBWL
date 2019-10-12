package ru.malifaux.programming.FTBWL.event.services.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.event.converters.EventInfoDtoConverter;
import ru.malifaux.programming.FTBWL.event.dto.EventInfoDto;
import ru.malifaux.programming.FTBWL.event.entity.EventEntity;
import ru.malifaux.programming.FTBWL.event.repository.EventRepository;
import ru.malifaux.programming.FTBWL.event.services.event.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventInfoDtoConverter eventInfoDtoConverter;

    @Override
    public List<EventInfoDto> getAll() {
        return eventRepository.findAll().stream().map(eventInfoDtoConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public EventEntity getByID(String id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public void save(EventEntity entity) {
        eventRepository.save(entity);
    }
}

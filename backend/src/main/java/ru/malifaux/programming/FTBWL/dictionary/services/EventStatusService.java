package ru.malifaux.programming.FTBWL.dictionary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventStatusEntity;
import ru.malifaux.programming.FTBWL.dictionary.repository.EventStatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventStatusService {

    private final EventStatusRepository eventStatusRepository;

    public EventStatusEntity findByCode(String code){
        return eventStatusRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Event status not found"));
    }

    public EventStatusEntity findById(String id){
        return eventStatusRepository.findById(id).orElseThrow(() -> new RuntimeException("Event status not found"));
    }

    public List<EventStatusEntity> findAll() {
        return eventStatusRepository.findAll();
    }
}

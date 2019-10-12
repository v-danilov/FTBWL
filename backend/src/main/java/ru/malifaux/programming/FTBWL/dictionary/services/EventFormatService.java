package ru.malifaux.programming.FTBWL.dictionary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventFormatEntity;
import ru.malifaux.programming.FTBWL.dictionary.repository.EventFormatRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventFormatService {

    private final EventFormatRepository eventFormatRepository;

    public EventFormatEntity findByCode(String code) {
        return eventFormatRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Format not found"));
    }

    public EventFormatEntity findById(String id) {
        return eventFormatRepository.findById(id).orElseThrow(() -> new RuntimeException("Format not found"));
    }

    public List<EventFormatEntity> findByGameSystem(UUID gameSystemId){
        return eventFormatRepository.findAllByGameSystemId(gameSystemId);
    }
}

package ru.malifaux.programming.FTBWL.dictionary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.dictionary.entity.FactionEntity;
import ru.malifaux.programming.FTBWL.dictionary.repository.FactionRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FactionService {

    private final FactionRepository factionRepository;

    public FactionEntity findByID(String id) {
        return factionRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public List<FactionEntity> findAll() {
        return factionRepository.findAll();
    }

    public List<FactionEntity> findAllByGameSystem(UUID id) {
        return factionRepository.findAllByGameSystemId(id);
    }
}

package ru.malifaux.programming.FTBWL.dictionary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.dictionary.entity.GameSystemEntity;
import ru.malifaux.programming.FTBWL.dictionary.repository.GameSystemRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameSystemService {

    private final GameSystemRepository gameSystemRepository;

    public GameSystemEntity findByCode(String code){
        return gameSystemRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Game system not found"));
    }

    public GameSystemEntity findById(String id){
        return gameSystemRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Game system not found"));
    }
}

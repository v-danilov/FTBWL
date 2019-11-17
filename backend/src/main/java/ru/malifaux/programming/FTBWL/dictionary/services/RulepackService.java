package ru.malifaux.programming.FTBWL.dictionary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.dictionary.entity.RulepackEntity;
import ru.malifaux.programming.FTBWL.dictionary.repository.RulepackRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RulepackService {
    
    private final RulepackRepository rulepackRepository;

    public RulepackEntity findByCode(String code) {
        return rulepackRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Format not found"));
    }

    public RulepackEntity findById(String id) {
        return rulepackRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Format not found"));
    }

    public List<RulepackEntity> findByGameSystem(UUID gameSystemId){
        return rulepackRepository.findAllByGameSystemId(gameSystemId);
    }
}

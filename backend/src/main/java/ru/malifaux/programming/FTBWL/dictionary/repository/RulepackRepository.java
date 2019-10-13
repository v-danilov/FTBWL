package ru.malifaux.programming.FTBWL.dictionary.repository;

import org.springframework.stereotype.Repository;
import ru.malifaux.programming.FTBWL.common.things.repository.DictionaryEntityRepository;
import ru.malifaux.programming.FTBWL.dictionary.entity.RulepackEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface RulepackRepository extends DictionaryEntityRepository <RulepackEntity> {

    List<RulepackEntity> findAllByGameSystemId(UUID id);
}

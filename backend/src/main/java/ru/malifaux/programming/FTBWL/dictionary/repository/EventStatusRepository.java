package ru.malifaux.programming.FTBWL.dictionary.repository;

import org.springframework.stereotype.Repository;
import ru.malifaux.programming.FTBWL.common.things.repository.DictionaryEntityRepository;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventStatusEntity;

@Repository
public interface EventStatusRepository extends DictionaryEntityRepository <EventStatusEntity>{

}
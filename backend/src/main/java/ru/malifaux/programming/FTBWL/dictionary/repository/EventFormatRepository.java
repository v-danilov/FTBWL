package ru.malifaux.programming.FTBWL.dictionary.repository;

import org.springframework.stereotype.Repository;
import ru.malifaux.programming.FTBWL.common.things.repository.DictionaryEntityRepository;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventFormatEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventFormatRepository extends DictionaryEntityRepository<EventFormatEntity, UUID> {

    List<EventFormatEntity> findAllByGameSystemId(UUID id);
}

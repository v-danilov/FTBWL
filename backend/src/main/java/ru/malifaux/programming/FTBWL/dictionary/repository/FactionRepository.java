package ru.malifaux.programming.FTBWL.dictionary.repository;

import ru.malifaux.programming.FTBWL.common.things.repository.JpaEntityRepository;
import ru.malifaux.programming.FTBWL.dictionary.entity.FactionEntity;

import java.util.List;
import java.util.UUID;


public interface FactionRepository extends JpaEntityRepository<FactionEntity, UUID> {
    FactionEntity findBySystemName(String systemName);

    List<FactionEntity> findAllByGameSystemId(UUID id);
}

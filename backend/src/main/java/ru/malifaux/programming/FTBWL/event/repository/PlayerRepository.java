package ru.malifaux.programming.FTBWL.event.repository;

import ru.malifaux.programming.FTBWL.common.things.repository.JpaEntityRepository;
import ru.malifaux.programming.FTBWL.event.entity.PlayerEntity;

import java.util.UUID;

public interface PlayerRepository extends JpaEntityRepository<PlayerEntity, UUID> {

}

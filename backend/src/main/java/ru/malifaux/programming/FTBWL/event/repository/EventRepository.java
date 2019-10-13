package ru.malifaux.programming.FTBWL.event.repository;

import org.springframework.stereotype.Repository;
import ru.malifaux.programming.FTBWL.common.things.repository.JpaEntityRepository;
import ru.malifaux.programming.FTBWL.event.entity.EventEntity;

@Repository
public interface EventRepository extends JpaEntityRepository<EventEntity> {
}

package ru.malifaux.programming.FTBWL.common.gateway.repository;

import org.springframework.stereotype.Repository;
import ru.malifaux.programming.FTBWL.common.gateway.entity.RoleEntity;
import ru.malifaux.programming.FTBWL.common.things.repository.JpaEntityRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaEntityRepository<RoleEntity, UUID> {

    List<RoleEntity> findAllByNameIn(Collection<String> names);
}

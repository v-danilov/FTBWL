package ru.malifaux.programming.FTBWL.common.gateway.repository;

import org.springframework.stereotype.Repository;
import ru.malifaux.programming.FTBWL.common.gateway.entity.FunctionEntity;
import ru.malifaux.programming.FTBWL.common.things.repository.JpaEntityRepository;

import java.util.Optional;

@Repository
public interface FunctionRepository extends JpaEntityRepository<FunctionEntity> {


}

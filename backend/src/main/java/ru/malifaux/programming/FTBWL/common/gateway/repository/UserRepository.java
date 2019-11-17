package ru.malifaux.programming.FTBWL.common.gateway.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.malifaux.programming.FTBWL.common.gateway.entity.UserEntity;
import ru.malifaux.programming.FTBWL.common.things.repository.JpaEntityRepository;

import java.util.UUID;


@Repository
public interface UserRepository extends JpaEntityRepository<UserEntity, UUID> {

    @Query("SELECT u FROM #{#entityName} u LEFT JOIN FETCH u.roles AS r LEFT JOIN FETCH r.functions WHERE u.username = ?1")
    UserEntity findByUsername(String username);

}

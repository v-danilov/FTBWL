package ru.malifaux.programming.FTBWL.common.things.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface DictionaryEntityRepository<T, E extends Serializable> extends JpaEntityRepository<T, E> {

    Optional<T> findByCode(String code);
}

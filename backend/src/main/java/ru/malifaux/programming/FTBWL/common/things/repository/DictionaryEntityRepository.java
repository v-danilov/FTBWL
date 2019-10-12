package ru.malifaux.programming.FTBWL.common.things.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface DictionaryEntityRepository <T> extends JpaEntityRepository <T> {

    Optional<T> findByCode(String code);
}

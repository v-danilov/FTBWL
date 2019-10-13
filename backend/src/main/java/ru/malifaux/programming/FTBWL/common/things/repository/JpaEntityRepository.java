package ru.malifaux.programming.FTBWL.common.things.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import ru.malifaux.programming.FTBWL.common.things.filter.Filter;

import java.util.List;
import java.util.Optional;

/**
 * Базовый класс репозитория, который реализует возможность построения графов
 */

@NoRepositoryBean
public interface JpaEntityRepository<T>
        extends EntityGraphJpaRepository<T, String>, EntityGraphJpaSpecificationExecutor<T> {

    default Page<T> findAll(Filter<T> filter, Pageable pageable) {
        return filter == null ? findAll(pageable) : findAll(filter.toSpecification(), pageable);
    }

    default List<T> findAll(Filter<T> filter) {
        return filter == null ? findAll() : findAll(filter.toSpecification());
    }

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1")
    Optional<T> getOneById(String id);


}

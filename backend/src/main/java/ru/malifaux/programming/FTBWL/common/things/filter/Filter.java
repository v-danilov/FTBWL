package ru.malifaux.programming.FTBWL.common.things.filter;

import org.springframework.data.jpa.domain.Specification;

/**
 * Фильтр для репозиториев
 */

@FunctionalInterface
public interface Filter<T> {
    Specification<T> toSpecification();
}

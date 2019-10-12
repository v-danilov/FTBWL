package ru.malifaux.programming.FTBWL.common.gateway.specifications;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.malifaux.programming.FTBWL.common.gateway.entity.RoleEntity;
import ru.malifaux.programming.FTBWL.common.things.specifications.CommonSpecifications;

import javax.persistence.criteria.JoinType;

@UtilityClass
public class RoleSpec {

    public static Specification<RoleEntity> hasName(String name) {
        return CommonSpecifications.contain("name", name);
    }

    public static Specification<RoleEntity> joinUsers() {
        return (root, query, cb) -> {
            if (Long.class != query.getResultType()) {
                root.fetch("users", JoinType.LEFT);
            }
            return cb.conjunction();
        };
    }
}
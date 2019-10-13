package ru.malifaux.programming.FTBWL.common.gateway.specifications;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import ru.malifaux.programming.FTBWL.common.gateway.entity.FunctionEntity;
import ru.malifaux.programming.FTBWL.common.gateway.entity.RoleEntity;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Collection;

import static ru.malifaux.programming.FTBWL.common.things.specifications.CommonSpecifications.contain;

@UtilityClass
public class FunctionSpec {

    public static Specification<FunctionEntity> hasCode(String code) {
        return contain("code", code);
    }

    public static Specification<FunctionEntity> hasValue(String value) {
        return contain("value", value);
    }

    public static Specification<FunctionEntity> hasRoles(Collection<String> roleIds){
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }
        return (root, query, cb) -> {
            Join<FunctionEntity, RoleEntity> roles = root.join("roles", JoinType.LEFT);
            return roles.get("id").in(roleIds);
        };
    }

}

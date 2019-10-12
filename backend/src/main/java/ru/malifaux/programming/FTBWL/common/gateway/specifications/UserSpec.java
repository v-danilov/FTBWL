package ru.malifaux.programming.FTBWL.common.gateway.specifications;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.malifaux.programming.FTBWL.common.gateway.entity.RoleEntity;
import ru.malifaux.programming.FTBWL.common.gateway.entity.UserEntity;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static ru.malifaux.programming.FTBWL.common.things.specifications.CommonSpecifications.contain;
import static ru.malifaux.programming.FTBWL.common.things.specifications.CommonSpecifications.is;

@UtilityClass
public class UserSpec {

    public static Specification<UserEntity> hasFirstName(String firstName) {
        return contain("firstName", firstName);
    }

    public static Specification<UserEntity> hasNickname(String nickname) {
        return contain("nickname", nickname);
    }

    public static Specification<UserEntity> hasLastName(String lastName) {
        return contain("lastName", lastName);
    }

    public static Specification<UserEntity> hasFullName(String fullName) {
        return contain("fullName", fullName);
    }

    public static Specification<UserEntity> isUserService(String isService) {
        return is("services", isService);
    }

    public static Specification<UserEntity> hasRole(String roleId) {
        if (roleId == null) {
            return null;
        }
        return (root, query, cb) -> {
            Join<UserEntity, RoleEntity> roles = root.join("roles");
            return cb.equal(roles.get("id"), roleId);
        };
    }

    public static Specification<UserEntity> joinRolesAndHasId(Collection<String> uuids) {
        return (root, query, cb) -> {
            if (isEmpty(uuids)) {
                if (Long.class != query.getResultType()) {  // if isn't count query
                    root.fetch("roles", JoinType.LEFT);
                }
                return cb.conjunction();
            } else {
                SetJoin<UserEntity, RoleEntity> roles;
                if (Long.class != query.getResultType()) {  // if isn't count query
                    roles = (SetJoin) root.fetch("roles");
                }else {
                    roles = (SetJoin) root.join("roles");
                }
                return roles.get("id").in(uuids);
            }
        };
    }

    public static Specification<UserEntity> hasCityIdOld(Collection<Integer> ids) {
        return (root, query, cb) -> {
            if (isEmpty(ids)) {
                return null;
            }
            return root.get("cityIdOld").in(ids);
        };
    }

    public static Specification<UserEntity> getUserByFunction (String name, String functionCode){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Object, Object> role = root.join("roles", JoinType.LEFT);
            Join<Object, Object> functions = role.join("functions", JoinType.LEFT);
            predicates.add(cb.equal(functions.get("code"), functionCode));
            if (!StringUtils.isEmpty(name)) {
                predicates.add(cb.like(cb.lower(root.get("fullName")), "%" + name.toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
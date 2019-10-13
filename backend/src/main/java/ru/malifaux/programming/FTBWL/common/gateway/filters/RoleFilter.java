package ru.malifaux.programming.FTBWL.common.gateway.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.malifaux.programming.FTBWL.common.gateway.entity.RoleEntity;
import ru.malifaux.programming.FTBWL.common.things.filter.Filter;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.malifaux.programming.FTBWL.common.gateway.specifications.RoleSpec.hasName;
import static ru.malifaux.programming.FTBWL.common.gateway.specifications.RoleSpec.joinUsers;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleFilter implements Filter<RoleEntity> {

    private String name;

    public Specification<RoleEntity> toSpecification() {
        return where(hasName(name))
                .and(joinUsers());
    }

}

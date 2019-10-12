package ru.malifaux.programming.FTBWL.common.gateway.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.malifaux.programming.FTBWL.common.gateway.entity.FunctionEntity;
import ru.malifaux.programming.FTBWL.common.things.filter.Filter;

import java.util.Set;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.malifaux.programming.FTBWL.common.gateway.specifications.FunctionSpec.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionFilter implements Filter<FunctionEntity> {

    /** Наименование функции */
    private String code;

    /** Описание */
    private String value;

    private Set<String> roles;

    public Specification<FunctionEntity> toSpecification() {
        return where(hasCode(code))
                .and(hasValue(value))
                .and(hasRoles(roles));

    }
}
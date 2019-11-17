package ru.malifaux.programming.FTBWL.common.gateway.filters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import ru.malifaux.programming.FTBWL.common.things.filter.Filter;
import ru.malifaux.programming.FTBWL.event.entity.PlayerEntity;

import static ru.malifaux.programming.FTBWL.common.gateway.specifications.PlayerSpec.eventIDEquals;

@Getter
@Setter
public class PlayerFilter implements Filter<PlayerEntity> {

    private String eventID;
    private String userID;


    @Override
    public Specification<PlayerEntity> toSpecification() {
        return Specification.where(eventIDEquals(eventID));
    }
}

package ru.malifaux.programming.FTBWL.common.gateway.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.malifaux.programming.FTBWL.event.entity.EventEntity;
import ru.malifaux.programming.FTBWL.event.entity.PlayerEntity;

import javax.persistence.criteria.Join;
import java.util.UUID;

public class PlayerSpec {

    public static Specification<PlayerEntity> eventIDEquals(String eventID) {
        return (root, query, cb) -> {
            Join<PlayerEntity, EventEntity> playerEvent = root.join("event");
            return cb.equal(playerEvent.get("id"), UUID.fromString(eventID));
        };
    }
}

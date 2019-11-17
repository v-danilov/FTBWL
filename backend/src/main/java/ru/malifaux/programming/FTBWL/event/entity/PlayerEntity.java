package ru.malifaux.programming.FTBWL.event.entity;

import lombok.Getter;
import lombok.Setter;
import ru.malifaux.programming.FTBWL.common.gateway.entity.UserEntity;
import ru.malifaux.programming.FTBWL.common.things.entity.AuditingEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.FactionEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "player")
@Setter
@Getter
public class PlayerEntity extends AuditingEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "faction_id")
    private FactionEntity faction;

    private int tp;
    private int vp;
    private int diff;
    private boolean proxyBot;
}

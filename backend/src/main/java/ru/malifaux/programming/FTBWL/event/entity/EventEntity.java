package ru.malifaux.programming.FTBWL.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malifaux.programming.FTBWL.common.gateway.entity.UserEntity;
import ru.malifaux.programming.FTBWL.common.things.entity.AuditingEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventFormatEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventStatusEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.PlaceEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.RulepackEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static ru.malifaux.programming.FTBWL.event.entity.EventEntity.EventEntityGraph.EVENT_ENTITY_WITH_ORGANIZER;
import static ru.malifaux.programming.FTBWL.event.entity.EventEntity.EventEntityGraph.SIMPLE_EVENT_ENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "event")
@NamedEntityGraphs({
        @NamedEntityGraph(name = SIMPLE_EVENT_ENTITY),
        @NamedEntityGraph(name = EVENT_ENTITY_WITH_ORGANIZER,
                attributeNodes = {@NamedAttributeNode("organiser")})
})
public class EventEntity extends AuditingEntity {

    /** Название эвента*/
    private String name;

    /** Статус эвента*/
    @ManyToOne
    @JoinColumn(name = "event_status_id")
    private EventStatusEntity status;

    /** Стоимость участия*/
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;

    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "id", nullable = false)
    private PlaceEntity place;

    @NotNull
    private LocalDateTime timeStart;

    @NotNull
    private LocalDateTime timeEnd;

    /** Организатор турнира*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private UserEntity organiser;

    @NotBlank
    private Integer roundsCount;

    /** Формат проведения турнира*/
    @ManyToOne
    @JoinColumn(name = "event_format_id")
    private EventFormatEntity format;

    /**  Руллпак проведения турнира*/
    @ManyToOne
    @JoinColumn(name = "rule_pack_id")
    private RulepackEntity rulepack;

    //TODO: Добавить список раундов

    public static class EventEntityGraph {
        public static final String SIMPLE_EVENT_ENTITY = "event-entity-graph";
        public static final String EVENT_ENTITY_WITH_ORGANIZER = "event-organiser-entity-graph";
    }
}

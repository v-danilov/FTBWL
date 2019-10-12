package ru.malifaux.programming.FTBWL.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malifaux.programming.FTBWL.common.gateway.entity.UserEntity;
import ru.malifaux.programming.FTBWL.common.things.entity.AuditingEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventFormatEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventStatusEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.RulepackEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "event")
public class EventEntity extends AuditingEntity {

    /** Название эвента*/
    private String name;

    /** Статус эвента*/
    @ManyToOne
    @JoinColumn(name = "event_status_id")
    private EventStatusEntity status;

    /** Стоимость участия*/
    private BigDecimal price;

    private String currency_id;

    //TODO: Изменить на нормальную сущность
    @NotBlank
    private String place_id;

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
}

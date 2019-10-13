package ru.malifaux.programming.FTBWL.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import ru.malifaux.programming.FTBWL.common.things.entity.Identifiable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "event_status")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "event_status_value_uindex", columnNames = "value"),
        @UniqueConstraint(name = "event_status_code_uindex", columnNames = "code")})
public class EventStatusEntity  extends Identifiable {

        /** Значение*/
        @NotBlank
        @NaturalId(mutable = true)
        private String value;

        /** Код*/
        @NotBlank
        @NaturalId(mutable = true)
        private String code;
}

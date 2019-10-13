package ru.malifaux.programming.FTBWL.common.gateway.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import ru.malifaux.programming.FTBWL.common.things.entity.AuditingEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "code", callSuper = false)
@NoArgsConstructor
@Entity(name = "function")
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_code_key", columnNames = "code"))
public class FunctionEntity extends AuditingEntity {

    /** Код функции */
    @NotBlank
    @NaturalId(mutable = true)
    private String code;

    /** Описание */
    @NotBlank
    private String value;

    @ManyToMany(mappedBy = "functions", fetch = FetchType.LAZY)
    private Set<RoleEntity> roles;

}

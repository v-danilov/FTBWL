package ru.malifaux.programming.FTBWL.common.gateway.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import ru.malifaux.programming.FTBWL.common.things.entity.AuditingEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "name", callSuper = false)
@NoArgsConstructor
@Entity(name = "role")
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_name_key", columnNames = "name"))
public class RoleEntity extends AuditingEntity {

    @NotBlank
    @NaturalId(mutable = true)
    private String name;

    /** Функции, которые доступны для роли*/
    @Valid
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "function_cross",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "function_id")
    )
    private Set<FunctionEntity> functions;

    @Valid
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserEntity> usersWithRole;

}

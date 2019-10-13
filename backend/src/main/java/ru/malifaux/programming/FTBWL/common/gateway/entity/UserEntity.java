package ru.malifaux.programming.FTBWL.common.gateway.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NaturalId;
import ru.malifaux.programming.FTBWL.common.locations.dto.CityDto;
import ru.malifaux.programming.FTBWL.common.things.entity.AuditingEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uq_username_key", columnNames = "username"),
        @UniqueConstraint(name = "uq_code_key", columnNames = "code")})
public class UserEntity extends AuditingEntity {

    /** Логин */
    @NaturalId(mutable = true)
    private String username;

    /** Пароль */
    private String password;

    /** Код */
    @NotNull
    private String code;

    /** Имя */
    private String firstName;

    /** Фамилия */
    private String lastName;

    /** Ник */
    private String nickname;

    /** Полное имя */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Setter(AccessLevel.PRIVATE)
    private String fullName;

    /** Адрес для спама */
    @NotNull
    @Email
    private String email;

    /** Признак служебного пользователя */
    @NotNull
    private boolean service;

    /** Город */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int cityIdOld;

    @Transient
    private CityDto city;

    /** Роли пользователя */
    @Valid
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_cross",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @PrePersist
    @PreUpdate
    private void onPreUpdate() {
        fullName = Stream.of(lastName, " \"", nickname, "\" ",firstName)
                .filter(StringUtils::isNotBlank)
                .collect(joining(""));
    }
}

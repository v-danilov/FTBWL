package ru.malifaux.programming.FTBWL.common.gateway.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.malifaux.programming.FTBWL.common.gateway.entity.UserEntity;
import ru.malifaux.programming.FTBWL.common.things.filter.Filter;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.malifaux.programming.FTBWL.common.gateway.specifications.UserSpec.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFilter implements Filter<UserEntity> {

    /**
     * Имя
     */
    private String firstName;

    /**
     * Никнейм
     */
    private String nickname;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Полное имя
     */
    private String fullName;

    /**
     * Признак служебного пользователя
     */
    private String isService;

    /**
     * Город
     */
    private List<Integer> cities;

    /**
     * Роли пользователя
     */
    private List<String> roles;

    public Specification<UserEntity> toSpecification() {
        return where(hasFirstName(firstName))
                .and(hasNickname(nickname))
                .and(hasLastName(lastName))
                .and(hasFullName(fullName))
                .and(isUserService(isService))
                .and(joinRolesAndHasId(roles))
                .and(hasCityIdOld(cities));
    }
}

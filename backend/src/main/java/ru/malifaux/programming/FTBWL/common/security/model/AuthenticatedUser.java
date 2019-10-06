package ru.malifaux.programming.FTBWL.common.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ru.malifaux.programming.FTBWL.common.security.support.JwtUserClaims;

/**
 * DomainUser - Авторизованный пользователь
 *
 */
@CurrentUser
@Getter
public class AuthenticatedUser {

    @JsonProperty("id")
    private String userId;

    private String login;

    public AuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            AuthenticatedUser u = (AuthenticatedUser) authentication.getPrincipal();
            this.userId = u.getUserId();
            this.login = u.getLogin();
        }
    }

    public AuthenticatedUser(AuthenticatedUser u) {
        if (u != null) {
            this.userId = u.getUserId();
            this.login = u.getLogin();
        }
    }

    public AuthenticatedUser(JwtUserClaims u) {
        this.userId = u.getUserId();
        this.login = u.getLogin();
    }

    public AuthenticatedUser(
            String userId,
            String login
    ) {
        this.userId = userId;
        this.login = login;
    }

    public JwtUserClaims toJwtClaims() {
        return JwtUserClaims
                .builder()
                .userId(userId)
                .login(login)
                .build();
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", userId, login);
    }
}

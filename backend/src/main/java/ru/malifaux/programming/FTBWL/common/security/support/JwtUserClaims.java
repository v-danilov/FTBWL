package ru.malifaux.programming.FTBWL.common.security.support;

import java.util.Map;

import io.jsonwebtoken.impl.DefaultClaims;
import lombok.*;

/**
 * AuthenticatedUser - TODO: description
 *
 * @author Borisevich Eugene (EBorisevich)
 * @since 15.02.2017
 */
public class JwtUserClaims extends DefaultClaims {
    public static final String USER_ID = "userId";
    public static final String LOGIN = "login";

    @Builder
    public JwtUserClaims(
            String userId,
            String login
    ) {

        super();
        setValue(USER_ID, userId);
        setValue(LOGIN, login);
    }

    public JwtUserClaims(Map<String, Object> map) {
        super(map);
    }

    public String getUserId() {
        return getString(USER_ID);
    }

    public String getLogin() {
        return getString(LOGIN);
    }
}
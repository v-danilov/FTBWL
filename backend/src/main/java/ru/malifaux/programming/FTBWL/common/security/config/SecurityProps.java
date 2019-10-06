package ru.malifaux.programming.FTBWL.common.security.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SecurityProps - Свойства безопасности
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Setter @Getter
public class SecurityProps {

    @Value("${auth.login.path}")
    private String loginPath;

    @Value("${auth.self.path}")
    private String selfPath;

    @Value("${auth.token.path}")
    private String tokenPath;

    @Value("${auth.header.username}")
    private String usernameHeaderName;

    @Value("${auth.header.password}")
    private String passwordHeaderName;

    @Value("${auth.header.token}")
    private String tokenHeaderName;

    @Value("${jwt.secret}")
    private String jwtSecret;

    //Pandora box
    private String[] ingored = {
            "/webjars/**",
            "/websocket/**"
    };

}
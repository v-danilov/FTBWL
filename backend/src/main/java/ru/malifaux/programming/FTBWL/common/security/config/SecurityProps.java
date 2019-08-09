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

    @Value("${auth.login.path:/api/v1/authorization/login}")
    private String loginPath;

    @Value("${auth.self.path:/api/v1/administration/people/find_with_authorities}")
    private String selfPath;

    @Value("${auth.token.path:/api/v1/authorization/token}")
    private String tokenPath;

    @Value("${auth.header.username:X-Auth-Username}")
    private String usernameHeaderName;

    @Value("${auth.header.password:X-Auth-Password}")
    private String passwordHeaderName;

    @Value("${auth.header.token:X-Auth-Token}")
    private String tokenHeaderName;

    @Value("${jwt.secret:GremlinKey$#15}")
    private String jwtSecret;

    //Pandora box
    private String[] ingored = {
            "/webjars/**",
            "/websocket/**"
    };

}
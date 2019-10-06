package ru.malifaux.programming.FTBWL.common.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Класс для хранения токена аутентификации
 *
 */
public class JwtTokenAuth extends UsernamePasswordAuthenticationToken {

    public JwtTokenAuth(String token) {
        super(token, null);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public String getPrincipal() {
        return (String) super.getPrincipal();
    }
}

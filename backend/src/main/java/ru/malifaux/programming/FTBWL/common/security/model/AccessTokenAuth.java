package ru.malifaux.programming.FTBWL.common.security.model;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * DomainAuth - TODO: description
 *
 */
public class AccessTokenAuth extends PreAuthenticatedAuthenticationToken {

    public AccessTokenAuth(AuthenticatedUser user, String token) {
        super(user, token, null);
    }

    /**
     * @return текущий залогиненный пользователь
     */
    @Override
    public AuthenticatedUser getPrincipal() {
        return (AuthenticatedUser) super.getPrincipal();
    }

    /**
     * @return  JWT токен
     */
    @Override
    public String getCredentials() {
        return (String) super.getCredentials();
    }
}

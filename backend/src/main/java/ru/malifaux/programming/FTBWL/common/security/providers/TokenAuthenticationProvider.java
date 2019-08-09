package ru.malifaux.programming.FTBWL.common.security.providers;

import static org.springframework.util.StringUtils.hasText;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ru.malifaux.programming.FTBWL.common.security.config.SecurityProps;
import ru.malifaux.programming.FTBWL.common.security.model.AccessTokenAuth;
import ru.malifaux.programming.FTBWL.common.security.model.AuthenticatedUser;
import ru.malifaux.programming.FTBWL.common.security.model.JwtTokenAuth;
import ru.malifaux.programming.FTBWL.common.security.support.JwtTokenUtils;
import ru.malifaux.programming.FTBWL.common.security.support.JwtUserClaims;

/**
 * Custom token generator
 */
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SecurityProps securityProps;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        if (!hasText(token)) {
            throw new AuthenticationServiceException("Invalid token");
        }

        try {
            JwtUserClaims details = JwtTokenUtils.parseToken(token, securityProps.getJwtSecret());
            return new AccessTokenAuth(new AuthenticatedUser(details), token);
        } catch (Exception e) {
            throw new AuthenticationServiceException("Invalid token", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtTokenAuth.class.isAssignableFrom(authentication);
    }

}

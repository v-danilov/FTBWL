package ru.malifaux.programming.FTBWL.common.security.filters;

import static org.springframework.util.StringUtils.hasText;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;
import ru.malifaux.programming.FTBWL.common.security.config.SecurityProps;
import ru.malifaux.programming.FTBWL.common.security.model.JwtTokenAuth;


/**
 * JwtTokenFilter - Фильтр авторизации
 *
 */
public class JwtTokenFilter extends GenericFilterBean implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource =
            new WebAuthenticationDetailsSource();

    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    private final AuthenticationManager mgr;

    private final String tokenHeaderName;

    public JwtTokenFilter(AuthenticationManager mgr, SecurityProps secProps) {
        Assert.notNull(mgr, "AuthenticationManager can not be null");
        Assert.notNull(secProps, "SecurityProperties can not be null");
        this.mgr = mgr;
        this.tokenHeaderName = secProps.getTokenHeaderName();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    /**
     * Check whether all required properties have been set.
     */
    @Override
    public void afterPropertiesSet() {
        try {
            super.afterPropertiesSet();
        } catch (ServletException e) {
            // convert to RuntimeException for passivity on afterPropertiesSet signature
            throw new RuntimeException(e);
        }
        Assert.notNull(mgr, "An AuthenticationManager must be set");
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (logger.isDebugEnabled()) {
            logger.debug("Checking secure context token: " + authentication);
        }
        if (authentication == null || !authentication.isAuthenticated()) {
            try {
                Authentication authResult = doAuthenticate(request, response);
                if (authResult == null) {
                    throw new PreAuthenticatedCredentialsNotFoundException("No token found in request");
                }
                successfulAuthentication(request, response, authResult);
            } catch (AuthenticationException failed) {
                // Authentication failed
                unsuccessfulAuthentication((HttpServletRequest) req, (HttpServletResponse) res, failed);
                return;
            }
        }

        logger.debug("TokenAuthFilter is passing request down the filters chain");
        chain.doFilter(req, res);
    }

    /**
     * Do the actual authentication for a pre-authenticated user.
     */
    private Authentication doAuthenticate(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String token = request.getHeader(tokenHeaderName);
        if (!hasText(token)) {
            if (logger.isDebugEnabled()) {
                logger.debug("No token found in request");
            }
            return null;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to authenticate user by token: " + token);
        }
        JwtTokenAuth authRequest = new JwtTokenAuth(token);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return mgr.authenticate(authRequest);
    }

    /**
     * Puts the <code>Authentication</code> instance returned by the authentication
     * manager into the secure context.
     */
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response, Authentication authResult
    ) throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success. Updating SecurityContextHolder to contain: " + authResult);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        // Fire event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }
    }

    /**
     * Ensures the authentication object in the secure context is set to null when
     * authentication fails.
     * <p>
     * Caches the failure exception as a request attribute
     */
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response, AuthenticationException failed
    ) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString(), failed);
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug("Delegating to authentication failure handler " + failureHandler);
        }
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        Assert.notNull(failureHandler, "failureHandler cannot be null");
        this.failureHandler = failureHandler;
    }

    protected AuthenticationFailureHandler getFailureHandler() {
        return failureHandler;
    }

}

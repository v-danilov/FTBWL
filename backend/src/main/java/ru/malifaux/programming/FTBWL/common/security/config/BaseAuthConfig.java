package ru.malifaux.programming.FTBWL.common.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import ru.malifaux.programming.FTBWL.common.security.filters.JwtTokenFilter;
import ru.malifaux.programming.FTBWL.common.security.providers.TokenAuthenticationProvider;

/**
 * BaseAuthConfig - Базовый класс аутентификации для сервисов через JWT-токен.
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class BaseAuthConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(getSecurityProps().getIngored());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO: check properties HttpSecurity
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/public").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        AuthenticationManager mgr = authenticationManager();
        http.addFilterAfter(new JwtTokenFilter(mgr, getSecurityProps()), AbstractPreAuthenticatedProcessingFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenProvider());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Although this seems like useless code, it's required to prevent spring boot auto-configuration
        return super.authenticationManagerBean();
    }

    @Bean
    public TokenAuthenticationProvider tokenProvider() {
        return new TokenAuthenticationProvider();
    }

    @Bean
    public SecurityProps getSecurityProps() {
        return new SecurityProps();
    }
}

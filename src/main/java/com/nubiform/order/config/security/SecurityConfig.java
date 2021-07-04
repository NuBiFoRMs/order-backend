package com.nubiform.order.config.security;

import com.nubiform.order.config.security.jwt.JwtAccessDeniedHandler;
import com.nubiform.order.config.security.jwt.JwtAuthenticationEntryPoint;
import com.nubiform.order.config.security.jwt.JwtAuthenticationFilter;
import com.nubiform.order.controller.AuthController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.debug:false}")
    boolean debugMode;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(debugMode)
                .ignoring()
                .antMatchers("/v3/api-docs/**")
                .antMatchers("/swagger-ui.html", "/swagger-ui/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .mvcMatchers(AuthController.API_V1_AUTH_URI + "/**").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAccessDeniedHandler())
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

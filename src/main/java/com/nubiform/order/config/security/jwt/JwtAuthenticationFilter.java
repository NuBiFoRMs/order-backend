package com.nubiform.order.config.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nubiform.order.config.security.jwt.JwtConstant.AUTHORIZATION_HEADER;
import static com.nubiform.order.config.security.jwt.JwtConstant.BEARER;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        log.debug("doFilter: {}", httpServletRequest.getRequestURI());

        String token = resolveToken(httpServletRequest);
        log.debug("token: {}", token);

        if (org.springframework.util.StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            httpServletResponse.setHeader(AUTHORIZATION_HEADER, String.join(" ", BEARER, token));
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.isNotEmpty(bearerToken) && StringUtils.startsWith(bearerToken, BEARER)) {
            return bearerToken.replace(BEARER, "").trim();
        }
        return null;
    }
}

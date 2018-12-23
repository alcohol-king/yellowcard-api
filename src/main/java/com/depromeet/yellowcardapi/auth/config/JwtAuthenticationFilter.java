package com.depromeet.yellowcardapi.auth.config;

import com.depromeet.yellowcardapi.auth.exception.InvalidTokenException;
import com.depromeet.yellowcardapi.auth.service.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            Authentication authentication = tokenProvider.getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (InvalidTokenException e) {
        }

        filterChain.doFilter(request, response);
    }
}

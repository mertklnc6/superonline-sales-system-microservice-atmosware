package com.turkcell.sol.core.config;


import com.turkcell.sol.core.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseSecurityService {
    private final JwtAuthFilter jwtAuthFilter;
    private final com.turkcell.sol.core.config.CustomAccessDeniedHandler customAccessDeniedHandler;
    private final com.turkcell.sol.core.config.CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public HttpSecurity securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(ex -> ex.authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(customAccessDeniedHandler));
        return http;
    }
}

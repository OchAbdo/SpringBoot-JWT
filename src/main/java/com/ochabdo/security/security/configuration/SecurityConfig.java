package com.ochabdo.security.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.ochabdo.security.web.dto.Permission.ADMIN_CREATE;
import static com.ochabdo.security.web.dto.Permission.ADMIN_DELETE;
import static com.ochabdo.security.web.dto.Permission.ADMIN_READ;
import static com.ochabdo.security.web.dto.Permission.ADMIN_UPDATE;
import static com.ochabdo.security.web.dto.Permission.STUDIANT_CREATE;
import static com.ochabdo.security.web.dto.Permission.STUDIANT_DELETE;
import static com.ochabdo.security.web.dto.Permission.STUDIANT_READ;
import static com.ochabdo.security.web.dto.Permission.STUDIANT_UPDATE;
import static com.ochabdo.security.web.dto.Role.ADMIN;
import static com.ochabdo.security.web.dto.Role.STUDIANT;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthentificationFilter jwtAuthFilter ;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler ;

    private static final String[] WHITE_LIST_URL = { "/auth/**", "/v2/api-docs", "/v3/api-docs",
			"/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
			"/api/test/**", "/authenticate" };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
    {
        http
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/studiant/**").hasAnyRole(ADMIN.name(), STUDIANT.name())
                                .requestMatchers(HttpMethod.GET, "/studiant/**").hasAnyAuthority(ADMIN_READ.name(), STUDIANT_READ.name())
                                .requestMatchers(HttpMethod.POST, "/studiant/**").hasAnyAuthority(ADMIN_CREATE.name(), STUDIANT_CREATE.name())
                                .requestMatchers(HttpMethod.PUT, "/studiant/**").hasAnyAuthority(ADMIN_UPDATE.name(), STUDIANT_UPDATE.name())
                                .requestMatchers(HttpMethod.DELETE, "/studiant/**").hasAnyAuthority(ADMIN_DELETE.name(),STUDIANT_DELETE.name())
                        .anyRequest()
                        .authenticated())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
        
        return http.build();

    }
    
}

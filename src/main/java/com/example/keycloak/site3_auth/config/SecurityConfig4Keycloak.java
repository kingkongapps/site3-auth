package com.example.keycloak.site3_auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig4Keycloak {

    private final KeycloakLogoutHandler keycloakLogoutHandler;

    public SecurityConfig4Keycloak(KeycloakLogoutHandler logoutHandler) {
        this.keycloakLogoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //
        http
                .csrf(csrf -> csrf.disable())
                // /public/** 경로는 인증 없이 접근 가능하도록 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index.html").permitAll()
                        .requestMatchers("/img/**", "/js/**", "/css/**", "/fonts/**").permitAll()
                        .anyRequest().authenticated()
                )
                // OAuth2 로그인 처리 (Keycloak 로그인 페이지로 리다이렉션)
                .oauth2Login(Customizer.withDefaults())
                .oauth2Login(oauth2 -> oauth2.successHandler(successHandler()))
                // 리소스 서버로서 JWT 토큰 검증
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler(keycloakLogoutHandler)
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (((request, response, authentication) -> {
            System.out.println("LOGIN_SUCCESS............");
            response.sendRedirect("/");
        }));
    }
}
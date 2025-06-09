package com.example.CTDT_APP.config;

import com.example.CTDT_APP.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    public static final String[] AUTH_ENDPOINTS = {
            "/api/v1/tai-khoan/dang-nhap",
            "/api/v1/tai-khoan/dang-ki",
    };
    public static final String[] EDUCATION_PROGRAM_ENDPOINTS = {
            "api/v1/ctdt/**",
            "api/v1/ke-hoach-hoc-tap/**",
            "api/v1/ki-hoc/**",
            "api/v1/mon-hoc/**",
            "api/v1/nam-dao-tao/**",
    };

    public static final String[] COMMON_ENDPOINTS = {
            "api/v1/bac-dao-tao/**", "api/v1/nganh-dao-tao/**",
            "api/v1/chuyen-nganh/**", "api/v1/khoa/**",
            "api/v1/mon-hoc/**", "api/v1/khoi-kien-thuc/**",
            "api/v1/he-dao-tao/**",
    };

    private final JwtFilter jwtFilter;
    private final MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> corsConfigurationSource())
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers(AUTH_ENDPOINTS).permitAll()
                            .requestMatchers(HttpMethod.GET, EDUCATION_PROGRAM_ENDPOINTS).permitAll()
                            .requestMatchers("api/v1/nhan-vien/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, COMMON_ENDPOINTS).hasAnyRole("ADMIN", "EMPLOYEE")
                            .requestMatchers(HttpMethod.POST, EDUCATION_PROGRAM_ENDPOINTS).hasAnyRole("ADMIN", "EMPLOYEE")
                            .anyRequest().hasRole("EMPLOYEE");
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new AccessDeniedHandlerImpl())
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:8080", "http://localhost:3000", "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}



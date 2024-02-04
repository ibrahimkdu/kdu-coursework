package com.kdu.smarthome.config;

import com.kdu.smarthome.filter.JWTFIlter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {

    @Bean
    SecurityFilterChain securityFIlter(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/v1/house", "/api/v1/house/**"
                                , "/api/v1/room/**", "/api/v1/room",
                                "/api/v1/inventory", "/api/v1/inventory/**",
                                "/api/v1/device/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/v1/auth/**").permitAll().anyRequest().authenticated()).csrf().disable().cors();
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTFIlter jwtAuthFilter() {
        return new JWTFIlter();
    }
}

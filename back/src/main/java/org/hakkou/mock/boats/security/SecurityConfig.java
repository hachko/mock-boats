package org.hakkou.mock.boats.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((authorise) -> {
            authorise
            .antMatchers("/h2-ui/**").permitAll()
            .antMatchers("/users/**").hasRole("ADMIN")
            .antMatchers("/boats/**").hasRole("USER") 
            .anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults()).headers().frameOptions().disable();
        return http.build();
    }
}

package com.example.tableau.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class Securityconfig {
     @Bean 
 public SecurityFilterChain filterChain (HttpSecurity http) throws 
 Exception { 
    http.sessionManagement( session ->  
    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 

    .csrf( csrf -> csrf.disable()) 
    .cors(cors -> cors.configurationSource(new CorsConfigurationSource() 
{ 
            @Override 
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) { 
                CorsConfiguration cors = new CorsConfiguration(); 
                cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); 
                cors.setAllowedMethods(Collections.singletonList("*")); 
                cors.setAllowedHeaders(Collections.singletonList("*")); 
                cors.setExposedHeaders(Collections.singletonList("Authorization")); 
                return cors; 
            } 
        }))
    

    .authorizeHttpRequests( requests -> 
    requests.requestMatchers("/api/all/**").hasAnyAuthority("USER","ADMIN")
            .requestMatchers(HttpMethod.GET,"/api/getbyid/**").hasAnyAuthority("USER","ADMIN")
            .requestMatchers(HttpMethod.POST,"/api/addtab/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.PUT,"/api/updatetab/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.DELETE,"/api/droptab/**").hasAuthority("ADMIN")
    .anyRequest().authenticated() )
    .addFilterBefore(new JWTAuthorizationFilter(), 
                    UsernamePasswordAuthenticationFilter.class);
    return http.build();
 } 

} 





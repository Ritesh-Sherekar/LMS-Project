//package com.example.LMS_API_Getway.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//public class GatewaySecurityConfig {
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//
//        return http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange -> exchange
//                        .pathMatchers("/consumer/**").permitAll()  // public routes like login
//                        .anyExchange().authenticated()          // everything else must be JWT
//                )
//                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)   // disable default login
//                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
//                .build();
//    }
//}
//
//
//

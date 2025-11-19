package com.example.LMS_QueryService.configuration;

import com.example.LMS_QueryService.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/queryCustomer/getCustomerByID").hasAuthority("admin")
                        .requestMatchers("/queryCustomer/getAllCustomer").hasAuthority("admin")
                        .requestMatchers("/queryCustomer/getCustomerByUserName").hasAuthority("admin")
                        .requestMatchers("/queryCustomerDetails/getCustomerDetailsByID").hasAuthority("admin")
                        .requestMatchers("/queryCustomerDetails/getCustomerDetailsByCusID").hasAnyAuthority("admin", "user")
                        .requestMatchers("/queryCustomerDetails/getCustomerDetailsByCustomerTableID").hasAnyAuthority("admin", "user")
                        .requestMatchers("/queryLoan/getLoanByID").hasAnyAuthority("admin", "user", "manager")
                        .requestMatchers("/loan/applyLoan").hasAuthority("user")
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}

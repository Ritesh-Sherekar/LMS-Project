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
                        .requestMatchers("/queryCustomerDetails/getCustomerDetailsByID").hasAnyAuthority("admin", "manager")
                        .requestMatchers("/queryCustomerDetails/getCustomerDetailsByCusID").hasAnyAuthority("admin", "user", "manager")
                        .requestMatchers("/queryCustomerDetails/getCustomerDetailsByCustomerTableID").hasAnyAuthority("admin", "manager", "user")
                        .requestMatchers("/queryLoan/getLoanByID").hasAnyAuthority("admin", "user", "manager")
                        .requestMatchers("/queryLoan/getLoanByLoanStatus").hasAnyAuthority("manager", "admin")
                        .requestMatchers("/queryEmi/getEmiByID").hasAnyAuthority( "manager", "admin")
                        .requestMatchers("/queryEmi/getEmiByLoanID").hasAnyAuthority("user", "manager", "admin")
                        .requestMatchers("/queryEmiPayment/getEmiPaymentByID").hasAnyAuthority("manager", "admin")
                        .requestMatchers("/queryEmiPayment/getEmiPaymentByLoanID").hasAnyAuthority("user", "manager", "admin")
                        .requestMatchers("/queryEmiPayment/getLastEmiPayment").hasAnyAuthority( "manager", "admin")
                        .requestMatchers("/queryEmiPayment/getLastEmiPaymentByLoanID").hasAnyAuthority( "user","manager", "admin")
                        .requestMatchers("/queryEmiPayment/getAllEmiPayment").hasAnyAuthority( "manager", "admin")
                        .requestMatchers("/queryUniversal/getUniversal").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}

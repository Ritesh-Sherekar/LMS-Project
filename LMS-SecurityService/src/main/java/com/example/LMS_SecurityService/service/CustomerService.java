package com.example.LMS_SecurityService.service;

import com.example.LMS_SecurityService.dto.CustomerDTO;
import com.example.LMS_SecurityService.dto.LoginDTO;
import com.example.LMS_SecurityService.dto.TokenDTO;
import com.example.LMS_SecurityService.entity.Customer;
import com.example.LMS_SecurityService.entity.Role;
import com.example.LMS_SecurityService.repository.CustomerRepo;
import com.example.LMS_SecurityService.repository.RoleRepo;
import com.example.LMS_SecurityService.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    // Register Customer
    public Customer registerCustomer(CustomerDTO customer){
        Customer c = new Customer();
        c.setUsername(customer.getUsername());
        c.setPassword(passwordEncoder.encode(customer.getPassword()));
        System.out.println("First");

        Set<Role> roles = new HashSet<>();
        for (String roleName : customer.getRoles()){
            Role role = roleRepo.findByRoleName(roleName).orElseThrow();

            roles.add(role);
        }
        if (!roles.isEmpty()){
            c.setRoles(roles);
        }
        System.out.println("second");
        return customerRepo.save(c);
    }

    // Login User
    public TokenDTO loginUser(LoginDTO loginDTO){
        Customer byUserName = customerRepo.findByUsername(loginDTO.getUsername());

        if (byUserName == null){
            throw new RuntimeException("User Not Found");
        }else {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticate.getName());

            if (authenticate.isAuthenticated()){
                String activeToken = jwtService.generateToken(userDetails);
                String refreshToken = jwtService.generateRefreshToken(userDetails);

                return new TokenDTO(activeToken, refreshToken);
            }
        }
        return null;
    }

    // Generate Active Token From Refresh Token
    public TokenDTO getActiveTokenFromRefreshToken(String refreshToken){
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String activeToken = jwtService.generateToken(userDetails);
        String refreshToken1 = jwtService.generateRefreshToken(userDetails);

        return new TokenDTO(activeToken, refreshToken1);
    }

    // Get User
    public List<Customer> getCustomer(){
        List<Customer> all = customerRepo.findAll();
        return all;
    }
}

package com.example.LMS_API_Getway.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        System.out.println("Header " + authHeader);

        // Allow public routes
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        String token = authHeader.substring(7);

        try {
            if ("refresh".equals(jwtService.extractTokenType(token))) {
                return unauthorized(exchange, "Token type 'refresh' is not allowed");
            }

            Claims claims = jwtService.extractAllClaims(token);

            List<Map<String, String>> roleMaps = claims.get("roles", List.class);
            List<String> roles = roleMaps.stream()
                    .map(r -> r.get("authority"))
                    .toList();

            String roleHeader = String.join(",", roles);

            // ⬅ MUTATE REQUEST WITH CUSTOM HEADERS
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-UserName", claims.getSubject())
                    .header("X-Role", roleHeader)
                    .build();

            // ⬅ VERY IMPORTANT: SET MUTATED REQUEST BACK INTO EXCHANGE
            ServerWebExchange mutatedExchange = exchange.mutate()
                    .request(mutatedRequest)
                    .build();

            return chain.filter(mutatedExchange);

        } catch (Exception e) {
            log.error("JWT error: {}", e.getMessage());
            return unauthorized(exchange, "Invalid or expired token");
        }
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String msg) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}


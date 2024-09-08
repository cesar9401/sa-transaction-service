package com.cesar31.transaction.infrastructure.adapters.input.rest.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("request: {} {}", request.getMethod(), request.getRequestURI());

        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.replace("Bearer ", "");
            try {
                if (jwtService.isValid(token)) {
                    var claims = jwtService.extractClaims(token);
                    var username = claims.getSubject();
                    var organizationId = claims.get("orgId", String.class);
                    var userId = claims.get("userId", String.class);
                    List<Map<String, String>> authoritiesList = (List<Map<String, String>>) claims.get("authorities");

                    var authorities = authoritiesList
                            .stream()
                            .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                            .toList();

                    var authData = new SaAuthenticationToken(
                            username,
                            null,
                            authorities,
                            UUID.fromString(userId),
                            organizationId != null && !organizationId.isEmpty() ? UUID.fromString(organizationId) : null
                    );

                    SecurityContextHolder.getContext().setAuthentication(authData);
                }
            } catch (Exception e) {
                log.error("JWT token validation failed", e);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("text/plain");
                response.getWriter().write(e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

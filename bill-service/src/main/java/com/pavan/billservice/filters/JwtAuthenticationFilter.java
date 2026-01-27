package com.pavan.billservice.filters;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request);
        System.out.println("JWT Filter executed, token = " + token);

        if (token != null) {
            authenticate(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        Claims claims = jwtTokenProvider.validateAndGetClaims(token);
        System.out.println("claims" + claims.getSubject());
        String userId = claims.get("userId", String.class);
        System.out.println("userId" + userId);

        System.out.println("Authentication set in SecurityContext");

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        UUID.fromString(userId), null, Collections.emptyList()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

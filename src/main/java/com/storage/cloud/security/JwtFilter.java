package com.storage.cloud.security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.replace("Bearer ", "");

            if(jwtUtil.isTokenValid(jwt)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Not Authorized");
            }
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Not Authorized");
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/api/registration".equals(path) || "/api/login".equals(path);
    }
}

package com.praticing.tarefas.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	 @Autowired
	    private JwtUtil jwtUtil;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	        final String token = request.getHeader("Authorization");
	        String username = null;

	        if (token != null && token.startsWith("Bearer ")) {
	            try {
	                // Extrai o nome do usuário do token
	                username = jwtUtil.extractUsername(token.substring(7));
	            } catch (Exception e) {
	                System.out.println("Token inválido ou expirado.");
	            }
	        }

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            if (jwtUtil.validateToken(token.substring(7), username)) {
	                var authentication = new UsernamePasswordAuthenticationToken(username, null, null);
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }
	        }

	        filterChain.doFilter(request, response);
	    }
}

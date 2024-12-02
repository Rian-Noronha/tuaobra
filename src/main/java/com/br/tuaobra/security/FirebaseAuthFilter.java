package com.br.tuaobra.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FirebaseAuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7); // Remover "Bearer " do token

			try {
				FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
				String uid = decodedToken.getUid();

				// Adicionar o UID do usuário ao contexto de segurança do Spring
				SecurityContextHolder.getContext().setAuthentication(
						(Authentication) new UsernamePasswordAuthenticationToken(uid, null, Collections.emptyList()));
			} catch (FirebaseAuthException e) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Invalid Firebase token");
				return;
			}
		}

		filterChain.doFilter(request, response);

	}

}

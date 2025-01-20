package com.br.tuaobra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.tuaobra.model.CasaConstrucao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

	@Value("${api.token.secret}")
	String my_secret;

	public String generateToken(CasaConstrucao casa) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(my_secret);
			String token = JWT.create().withIssuer("tuaobra").withSubject(casa.getEmail())
					.withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))).sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("ERROR AO GERAR O TOKEN", e);
		}
	}

	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(my_secret);
			return JWT.require(algorithm).withIssuer("tuaobra").build().verify(token).getSubject();
		} catch (JWTVerificationException e) {
			return "";
		}

	}

}

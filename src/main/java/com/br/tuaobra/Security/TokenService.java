package com.br.tuaobra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret; // Chave secreta para assinar os tokens JWT

    @Value("${jwt.expiration}")
    private Long expiration; // Tempo de expiração do token em milissegundos

    /**
     * Gera um token JWT com base no e-mail do usuário autenticado.
     * 
     * @param email O e-mail do usuário autenticado.
     * @return O token JWT gerado.
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Define o e-mail do usuário como identificador do token
                .setIssuedAt(new Date()) // Define a data de criação do token
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Define a data de expiração
                .signWith(SignatureAlgorithm.HS512, secret) // Assina o token com a chave secreta
                .compact(); // Retorna o token em formato JWT
    }

    /**
     * Valida um token JWT e retorna o e-mail do usuário, se válido.
     * 
     * @param token O token JWT a ser validado.
     * @return O e-mail do usuário, ou null se o token for inválido.
     */
    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret) // Configura a chave secreta para validação
                    .parseClaimsJws(token) // Faz o parsing do token
                    .getBody(); // Extrai o corpo (payload) do token
            return claims.getSubject(); // Retorna o "sub" (e-mail do usuário)
        } catch (Exception e) {
            return null; // Retorna null se o token for inválido ou expirado
        }
    }

    /**
     * Verifica se um token JWT está expirado.
     * 
     * @param token O token JWT a ser verificado.
     * @return true se o token estiver expirado, false caso contrário.
     */
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true; // Considera expirado em caso de erro na validação
        }
    }
}

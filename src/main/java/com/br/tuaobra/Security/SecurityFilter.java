package com.br.tuaobra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.tuaobra.repository.PedreiroRepository;
import com.br.tuaobra.model.Cliente;
import com.br.tuaobra.model.Pedreiro;
import com.br.tuaobra.repository.ClienteRepository;
import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PedreiroRepository pedreiroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        // Recupera o token do cabeçalho Authorization
        var token = this.recoverToken(request);

        if (token != null) {
            try {
                // Valida o token e obtém o email do usuário
                String email = this.tokenService.validateToken(token);

                // Tenta autenticar como pedreiro
                Optional<Pedreiro> pedreiro = pedreiroRepository.findByEmail(email);
                if (pedreiro.isPresent()) {
                    authenticateUser(pedreiro.get());
                } else {
                    // Caso não seja pedreiro, tenta autenticar como cliente
                    Optional<Cliente> cliente = clienteRepository.findByEmail(email);
                    cliente.ifPresent(this::authenticateUser);
                }
            } catch (Exception e) {
                // Loga erro caso o token seja inválido
                System.err.println("Erro ao validar token: " + e.getMessage());
            }
        }

        // Continua a execução da cadeia de filtros
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(UserDetails userDetails) {
        // Cria o objeto de autenticação e configura no contexto de segurança
        var authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {
        // Extrai o token do cabeçalho Authorization
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}

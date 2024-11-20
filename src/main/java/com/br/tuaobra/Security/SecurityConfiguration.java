package com.br.tuaobra.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    private AuthenticationConfiguration authenticationConfiguration;
// construtor para a injeção de dependências
        @Autowired
        private SecurityFilter securityFilter;
    
        public void SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
            this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/api/pedreiro/**").hasRole("PEDREIRO")  // Apenas PEDREIRO pode acessar essas rotas
                .requestMatchers(HttpMethod.GET, "/api/cliente/**").hasRole("CLIENTE")  // Apenas CLIENTE pode acessar essas rotas
                // Permite acesso para rotas gerais de clientes e pedreiros
                .requestMatchers(HttpMethod.GET, "/api/public/**").permitAll()  // Rota pública
                .anyRequest().authenticated()  // Qualquer outra rota precisa de autenticação
                )
                .addFilterBefore((Filter) securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


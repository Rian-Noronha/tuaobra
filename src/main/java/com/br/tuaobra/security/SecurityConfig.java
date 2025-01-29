package com.br.tuaobra.security;

import java.util.Arrays;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	private SecurityFilter securityFilter;
	
	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
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
	                    .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
	                    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
	                    .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
	                    .requestMatchers(HttpMethod.POST, "/api/cliente", "/api/pedreiro", "/api/demanda", "/api/demandacliente", "/api/pedreiro/email/{email}/demanda/{demandaId}",  "/api/casaconstrucao/{casaId}/{demandaId}/{emailcliente}").permitAll()
	                    .requestMatchers(HttpMethod.POST, "/api/auth/register/admin").hasRole("ADMINISTRADOR")
	                    .requestMatchers(HttpMethod.PUT, "/api/demanda/**").permitAll() 
	                    .requestMatchers(HttpMethod.GET, 
	                            "/api/pedreiros", 
	                            "/api/clientesvinculadospedreirodemanda/email/{email}", 
	                            "/api/pedreiro/{id}", 
	                            "/api/demandas", 
	                            "/api/demanda/{id}", 	                             
	                            "/api/demandacliente/{id}", 
	                            "/api/cliente/{id}",
	                            "/api/clientes", 
	                            "/api/demandascliente/email/{email}",
	                            "/api/casasconstrucao").permitAll()
	                    .requestMatchers(HttpMethod.GET, "/api/casaconstrucao/{id}", "/api/casaconstrucao/demandasclientevinculadocasa/email/{email}", "/api/casaconstrucao/clientesvinculados/email/{email}")
	                    .authenticated() 
	                    .anyRequest().authenticated()
	            )
	            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
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

	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200/")); // Customize with your front-end
																					// origin
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}

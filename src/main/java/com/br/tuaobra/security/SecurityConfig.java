package com.br.tuaobra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private FirebaseAuthFilter firebaseAuthFilter;

	private final AuthenticationConfiguration authenticationConfiguration;

	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
		this.authenticationConfiguration = authenticationConfiguration;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/api/auth/logincliente")
						.permitAll()
						.requestMatchers(HttpMethod.POST, "/api/cliente", "/api/demanda", "/api/demandacliente")
						.permitAll()
						.requestMatchers(HttpMethod.GET, "/api/demandas", "/api/demandascliente/email/{email}",
								"/api/clientes", "/api/cliente/{id}")
						.permitAll().requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**")
						.permitAll()

						.anyRequest().authenticated())
				.addFilterBefore(firebaseAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
	
	
	 	@Bean
	    public AuthenticationManager authenticationManager() throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	 	
	 	

}

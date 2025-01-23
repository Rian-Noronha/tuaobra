package com.br.tuaobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.tuaobra.repository.CasaConstrucaoRepository;

@Service
public class AuthServiceImpl implements UserDetailsService {

	@Autowired
	private CasaConstrucaoRepository casaConstrucaoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return casaConstrucaoRepository.findByEmail(username).get();
	}

}

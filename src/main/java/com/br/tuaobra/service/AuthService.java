package com.br.tuaobra.service;

import com.br.tuaobra.repository.CasaConstrucaoRepository;
import com.br.tuaobra.repository.ClienteRepository;
import com.br.tuaobra.repository.PedreiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private CasaConstrucaoRepository casaConstrucaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedreiroRepository pedreiroRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<? extends UserDetails> user;

        user = casaConstrucaoRepository.findByEmail(username);
        if (user.isPresent()) return user.get();

        user = clienteRepository.findByEmail(username);
        if (user.isPresent()) return user.get();

        user = pedreiroRepository.findByEmail(username);
        if (user.isPresent()) return user.get();

        throw new UsernameNotFoundException("Usuário não encontrado com o email: " + username);
    }
}

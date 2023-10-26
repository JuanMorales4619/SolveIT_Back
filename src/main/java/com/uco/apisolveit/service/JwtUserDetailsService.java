package com.uco.apisolveit.service;

import com.uco.apisolveit.domain.RedPerson;
import com.uco.apisolveit.repository.redperson.IRedPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IRedPersonRepository iredUserRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RedPerson user = iredUserRepository.findByUsername(username).block();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public Mono<RedPerson> save(RedPerson redUser) {
        redUser.setPassword(bcryptEncoder.encode(redUser.getPassword()));
        return iredUserRepository.save(redUser);
    }
    public Flux<RedPerson> getAll(){
        return iredUserRepository.findAll();
    }
}


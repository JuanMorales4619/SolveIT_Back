package com.uco.apisolveit.controller.jwt;

import com.uco.apisolveit.domain.JwtRequest;
import com.uco.apisolveit.domain.JwtResponse;
import com.uco.apisolveit.domain.RedPerson;
import com.uco.apisolveit.dto.redperson.RedPersonDTO;
import com.uco.apisolveit.jwt.JwtTokenUtil;
import com.uco.apisolveit.service.JwtUserDetailsService;
import com.uco.apisolveit.util.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/authenticate")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    //@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws GeneralException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public Mono<RedPerson> saveUser(@RequestBody RedPersonDTO userDTO)  {
        return userDetailsService.save(RedPerson.setData(userDTO));
    }
    @GetMapping("/get")
    public Flux<RedPerson> getAll(){
        return userDetailsService.getAll();
    }

    private void authenticate(String username, String password) throws GeneralException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw GeneralException.build("USER_DISABLED",e);
        } catch (BadCredentialsException e) {
            throw GeneralException.build("INVALID_CREDENTIALS", e);
        }
    }
}
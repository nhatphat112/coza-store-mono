package com.apigateway.manager;

import com.apigateway.client.UserServiceClient;
import com.apigateway.payload.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Configuration
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
         return userServiceClient.getUserByUsernameOrEmail(username,username).flatMap(userResponse ->{
            if (userResponse != null) {
                if(passwordEncoder.matches(password,userResponse.getPassword())) {
                    return Mono.just (new UsernamePasswordAuthenticationToken(username, userResponse.getPassword(), new ArrayList<>()));
                }
                return Mono.empty();
            }
            return Mono.empty();
        });
    }



}

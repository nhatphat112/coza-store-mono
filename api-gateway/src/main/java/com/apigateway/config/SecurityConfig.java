package com.apigateway.config;

import com.apigateway.manager.CustomAuthenticationManager;
import com.apigateway.payload.response.BaseResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(cors->cors.disable()).csrf(csrf ->csrf.disable())
                // custom 401 403 exception
//                .exceptionHandling(exception ->exception.authenticationEntryPoint((exchange, ex) -> {
//                    BaseResponse baseResponse = new BaseResponse();
//                    if (ex instanceof InsufficientAuthenticationException) {
//                        // drop in 403
//                        baseResponse.setStatusCode(403);
//                        baseResponse.setMessage("Access denied.");
//
//                    }else {
//                        // drop in 401
//                        baseResponse.setMessage("Unauthorized.");
//                        baseResponse.setStatusCode(401);
//
//                    }
//                    ServerHttpResponse response = exchange.getResponse();
//                    response.setStatusCode(HttpStatus.OK);
//                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                    return response.writeWith(Mono.just(response.bufferFactory().wrap(new Gson().toJson(baseResponse).getBytes())));
//                }))
                .authorizeExchange(authorize -> authorize.pathMatchers("/api/signin/**").permitAll())
                .build();
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
    // type hash
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Bean
    public ReactiveAuthenticationManager authenticationManager (){
        return authenticationManager;
    }


}

package com.apigateway.controller;

import com.apigateway.authentication.CustomAuthentication;
import com.apigateway.manager.CustomAuthenticationManager;
import com.apigateway.payload.request.SignInRequest;
import com.apigateway.payload.response.BaseResponse;
import com.apigateway.utils.JwtHelper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@CrossOrigin("*")
public class LoginController {
    private final CustomAuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final Gson gson = new Gson();
    private final JwtHelper jwtHelper;

    @Autowired
    public LoginController(CustomAuthenticationManager authenticationManager, JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }

    @RequestMapping(value = "/api/signin",method = RequestMethod.POST)
    public Mono<ResponseEntity<BaseResponse>> signIn(@RequestBody SignInRequest request) {
        logger.info("signIn Request: " + gson.toJson(request));
        return Mono.fromCallable(() ->
                        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()))
                                .switchIfEmpty(Mono.just((Authentication) new CustomAuthentication()))
                                .flatMap(authentication -> {
                                    BaseResponse baseResponse = new BaseResponse();
                                    if (authentication.isAuthenticated()) {
                                        logger.info("Authenticated");
                                        baseResponse.setMessage("Login success.");
                                        baseResponse.setStatusCode(200);
                                        baseResponse.setData(jwtHelper.generateToken(request.getUsername()));
                                    } else {
                                        logger.info("Un-Authenticated");
                                        baseResponse.setMessage("Unauthorized");
                                        baseResponse.setStatusCode(401);
                                    }
                                    logger.info("signIn Response: " + gson.toJson(baseResponse));
                                    return Mono.just(ResponseEntity.ok(baseResponse));
                                }))
                .flatMap(mono ->mono);
    }
}


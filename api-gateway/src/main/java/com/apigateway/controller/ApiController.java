package com.apigateway.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class ApiController {
    @Autowired
    private WebClient.Builder webClient;
    private Gson gson = new Gson();
    @GetMapping()
    public ResponseEntity<?> getAllUser(){
        Mono<ClientResponse> bool = webClient.build().get()
                .uri("http://user-service/api/user")
                .exchange();


        return new ResponseEntity<>(bool.subscribe().toString(), HttpStatus.OK);
    };
}

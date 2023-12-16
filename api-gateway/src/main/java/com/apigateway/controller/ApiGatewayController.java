package com.apigateway.controller;

import com.apigateway.payload.request.GetUserByUsernameOrEmailRequest;
import com.apigateway.payload.response.BaseResponse;
import com.apigateway.payload.response.GetUserByUsernameOrPasswordResponse;
import com.apigateway.utils.JwtHelper;
import com.google.gson.Gson;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiGatewayController {
    @Autowired
    private JwtHelper jwtHelper;
    private Gson gson = new Gson();
    @Autowired
    private WebClient.Builder webclient;
    private Logger logger = LoggerFactory.getLogger(ApiGatewayController.class);
    @GetMapping("/key/generate")
    public ResponseEntity<?> generateSecretKey(){
        logger.info("Request:Generate secret key");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Secret key");
        baseResponse.setData(jwtHelper.createSecretKey());
        logger.info("Response:"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @PostMapping("test")
    public ResponseEntity<?> test(){
        GetUserByUsernameOrEmailRequest request = GetUserByUsernameOrEmailRequest.builder()
                .email("")
                .username("nhatphat112")
                .build();
       BaseResponse baseResponse = webclient.build().post()
                .uri("http://user-service/api/user/get-user-by-username-or-email")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(gson.toJson(request))
               .retrieve()
               .bodyToMono(BaseResponse.class)
               .block();
        GetUserByUsernameOrPasswordResponse response =null;
       if(baseResponse.getStatusCode()==200){
               response = gson.fromJson(gson.toJson(baseResponse.getData()),GetUserByUsernameOrPasswordResponse.class);
       }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

package com.apigateway.controller;

import com.apigateway.payload.response.BaseResponse;
import com.apigateway.utils.JwtHelper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/key")
public class ApiGatewayController {
    @Autowired
    private JwtHelper jwtHelper;
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(ApiGatewayController.class);
    @GetMapping("/generate")
    public ResponseEntity<?> generateSecretKey(){
        logger.info("Request:Generate secret key");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Secret key");
        baseResponse.setData(jwtHelper.createSecretKey());
        logger.info("Response:"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}

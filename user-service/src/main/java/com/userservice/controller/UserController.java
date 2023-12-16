package com.userservice.controller;

import com.google.gson.Gson;
import com.userservice.payload.request.GetUserByUserNameOrEmailRequest;
import com.userservice.payload.response.BaseResponse;
import com.userservice.repository.AccountStatusRepository;
import com.userservice.service.imp.AccountStatusServiceImp;
import com.userservice.service.imp.UserServiceImp;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    AccountStatusServiceImp accountStatusServiceImp;
    @GetMapping("")
    public ResponseEntity<?> getAllUser(){
        logger.info("Request:");
        BaseResponse response = new BaseResponse();
        response.setMessage("List User");
        response.setStatusCode(200);
        response.setData(userServiceImp.getAllUser());
        logger.info("Response:"+gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/account-status")
    public ResponseEntity<?> getAllAccountStatus(){
        logger.info("Request:");
        BaseResponse response = new BaseResponse();
        response.setData(accountStatusServiceImp.getAllAccountStatus());
        response.setStatusCode(200);
        response.setMessage("List Account Status");
        logger.info("Response:"+gson.toJson(response));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping("/get-user-by-username-or-email")
    public ResponseEntity<?> getUserByUserNameOrEmail(@RequestBody GetUserByUserNameOrEmailRequest request){
        logger.info("Request:"+gson.toJson(request));
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Find user by username or email.");
        baseResponse.setStatusCode(200);
        baseResponse.setData(userServiceImp.findUser(request.getUsername(), request.getEmail()));
        logger.info("Response:"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }
}

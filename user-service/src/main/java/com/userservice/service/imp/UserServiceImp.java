package com.userservice.service.imp;

import com.userservice.payload.response.AllUserResponse;
import com.userservice.payload.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceImp  {
     AllUserResponse getAllUser();
     UserResponse findUser(String username, String email);
     UserResponse findUser(String username,String email,String password);
}

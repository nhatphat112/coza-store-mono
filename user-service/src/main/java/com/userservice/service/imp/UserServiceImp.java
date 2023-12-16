package com.userservice.service.imp;

import com.userservice.payload.response.AllUserResponse;
import com.userservice.payload.response.GetUserByUsernameOrPasswordResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceImp  {
     AllUserResponse getAllUser();
     GetUserByUsernameOrPasswordResponse findUser(String username, String email);
}

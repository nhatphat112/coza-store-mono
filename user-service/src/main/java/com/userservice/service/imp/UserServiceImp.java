package com.userservice.service.imp;

import com.userservice.payload.response.AllUserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceImp  {
     AllUserResponse getAllUser();
}

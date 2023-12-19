package com.userservice.service;

import com.userservice.entity.UserEntity;
import com.userservice.payload.response.AllUserResponse;
import com.userservice.payload.response.UserEntityResponse;
import com.userservice.payload.response.UserResponse;
import com.userservice.repository.UserRepository;
import com.userservice.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    public AllUserResponse getAllUser(){
        AllUserResponse response = new AllUserResponse();
        response.setUserList(new ArrayList<>());
        for (UserEntity user:userRepository.findAll()) {
            UserEntityResponse userEntityResponse = new UserEntityResponse();
            userEntityResponse.setId(user.getId());
            userEntityResponse.setUsername(user.getUsername());
            userEntityResponse.setFullName(user.getFullName());
            userEntityResponse.setPassword(user.getPassword());
            userEntityResponse.setEmail(user.getEmail());
            userEntityResponse.getRole().setId(user.getRole().getId());
            userEntityResponse.getRole().setRoleName(user.getRole().getRoleName());
            userEntityResponse.getRole().setDescription(user.getRole().getDescription());
            userEntityResponse.getAccountCategory().setAccountCategoryName(user.getAccountCategory().getAccountCategoryName());
            userEntityResponse.getAccountCategory().setId(user.getAccountCategory().getId());
            userEntityResponse.getAccountStatus().setAccountStatusName(user.getAccountStatus().getAccountStatusName());
            userEntityResponse.getAccountStatus().setId(user.getAccountStatus().getId());
            response.getUserList().add(userEntityResponse);
        }
        return response;
    }

    @Override
    public UserResponse findUser(String username, String email) {
        for(UserEntity user : userRepository.findByUsernameOrEmail(username,email)){
            return UserResponse.builder()
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .username(username)
                    .roleId(user.getRole().getId())
                    .accountCategoryId(user.getAccountCategory().getId())
                    .accountStatusId(user.getAccountStatus().getId())
                    .password(user.getPassword())
                    .build();
        }
        return null;
    }

    @Override
    public UserResponse findUser(String username, String email, String password) {
       for (UserEntity user : userRepository.findByUsernameOrEmailAndPassword(username,email,password)){
                return UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .roleId(user.getRole().getId())
                        .accountCategoryId(user.getAccountCategory().getId())
                        .fullName(user.getFullName())
                        .accountStatusId(user.getAccountStatus().getId())
                        .build();
        }
        return null;
    }
}

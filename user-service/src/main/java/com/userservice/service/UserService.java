package com.userservice.service;

import com.userservice.entity.UserEntity;
import com.userservice.payload.response.AllUserResponse;
import com.userservice.payload.response.UserReponse;
import com.userservice.payload.response.GetUserByUsernameOrPasswordResponse;
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
            UserReponse userReponse = new UserReponse();
            userReponse.setId(user.getId());
            userReponse.setUsername(user.getUsername());
            userReponse.setFullName(user.getFullName());
            userReponse.setPassword(user.getPassword());
            userReponse.setEmail(user.getEmail());
            userReponse.getRole().setId(user.getRole().getId());
            userReponse.getRole().setRoleName(user.getRole().getRoleName());
            userReponse.getRole().setDescription(user.getRole().getDescription());
            userReponse.getAccountCategory().setAccountCategoryName(user.getAccountCategory().getAccountCategoryName());
            userReponse.getAccountCategory().setId(user.getAccountCategory().getId());
            userReponse.getAccountStatus().setAccountStatusName(user.getAccountStatus().getAccountStatusName());
            userReponse.getAccountStatus().setId(user.getAccountStatus().getId());
            response.getUserList().add(userReponse);
        }
        return response;
    }

    @Override
    public GetUserByUsernameOrPasswordResponse findUser(String username, String email) {
        for(UserEntity user : userRepository.findByUsernameOrEmail(username,email)){
            return GetUserByUsernameOrPasswordResponse.builder()
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
}

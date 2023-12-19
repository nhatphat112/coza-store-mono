package com.userservice.payload.response;

import com.userservice.entity.AccountCategoryEntity;
import com.userservice.entity.AccountStatusEntity;
import com.userservice.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserEntityResponse {
    private int id;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private RoleEntity role = new RoleEntity();

    private AccountCategoryEntity accountCategory = new AccountCategoryEntity();

    private AccountStatusEntity accountStatus = new AccountStatusEntity();

}

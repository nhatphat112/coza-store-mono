package com.apigateway.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private int id;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private int roleId;

    private int accountCategoryId;

    private int accountStatusId;

}

package com.userservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserByUsernameOrEmailAndPasswordRequest {
    private String username;
    private String email;
    private String password;
}

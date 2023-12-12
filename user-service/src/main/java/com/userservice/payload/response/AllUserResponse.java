package com.userservice.payload.response;

import com.userservice.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllUserResponse {
    private List<UserReponse> userList = new ArrayList<>();
}

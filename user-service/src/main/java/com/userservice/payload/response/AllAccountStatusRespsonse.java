package com.userservice.payload.response;

import com.userservice.entity.AccountStatusEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllAccountStatusRespsonse {
    private List<AccountStatusResponse> accountStatusResponseList = new ArrayList<>();
}

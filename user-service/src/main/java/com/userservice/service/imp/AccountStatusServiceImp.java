package com.userservice.service.imp;

import com.userservice.payload.response.AllAccountStatusRespsonse;
import org.springframework.stereotype.Service;

@Service
public interface AccountStatusServiceImp {
    AllAccountStatusRespsonse getAllAccountStatus();
}

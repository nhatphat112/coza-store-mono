package com.userservice.service;

import com.userservice.entity.AccountCategoryEntity;
import com.userservice.entity.AccountStatusEntity;
import com.userservice.payload.response.AccountStatusResponse;
import com.userservice.payload.response.AllAccountStatusRespsonse;
import com.userservice.repository.AccountStatusRepository;
import com.userservice.service.imp.AccountStatusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountStatusService implements AccountStatusServiceImp {
    @Autowired
    private AccountStatusRepository repository;
    @Override
    public AllAccountStatusRespsonse getAllAccountStatus() {
        AllAccountStatusRespsonse allRespsonse = new AllAccountStatusRespsonse();
        for(AccountStatusEntity status : repository.findAll()){
            AccountStatusResponse response = new AccountStatusResponse();
            response.setId(status.getId());
            response.setAccountStatusName(status.getAccountStatusName());
            allRespsonse.getAccountStatusResponseList().add(response);
        }
        return allRespsonse;
    }
}

package com.userservice.repository;

import com.userservice.entity.AccountStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatusEntity,Integer> {
    @Override
    List<AccountStatusEntity> findAll();
}

package com.userservice.repository;

import com.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    List<UserEntity> findAll();
    List<UserEntity> findByUsernameOrEmail(String username,String email);
    Optional<UserEntity> findById(Integer id);
}

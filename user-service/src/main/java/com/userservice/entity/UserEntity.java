package com.userservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column( name = "email")
    private String email;
    @ManyToOne()
    @JoinColumn(name = "role_id",nullable = false)
    private RoleEntity role;
    @ManyToOne()
    @JoinColumn(name = "account_category_id",nullable = false)
    private AccountCategoryEntity accountCategory;
    @ManyToOne()
    @JoinColumn(name = "account_status_id")
    private AccountStatusEntity accountStatus;

    public UserEntity() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public AccountCategoryEntity getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(AccountCategoryEntity accountCategory) {
        this.accountCategory = accountCategory;
    }

    public AccountStatusEntity getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatusEntity accountStatus) {
        this.accountStatus = accountStatus;
    }
}

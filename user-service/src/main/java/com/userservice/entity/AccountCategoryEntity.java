package com.userservice.entity;

import jakarta.persistence.*;
import org.yaml.snakeyaml.events.Event;

import java.util.Set;

@Entity(name = "account_category")

public class AccountCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "account_category_name")
    private String accountCategoryName;
    @OneToMany(mappedBy = "accountCategory")
    private Set<UserEntity> userEntities;

    public AccountCategoryEntity() {
    }

    public AccountCategoryEntity(int id, String accountCategoryName, Set<UserEntity> userEntities) {
        this.id = id;
        this.accountCategoryName = accountCategoryName;
        this.userEntities = userEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountCategoryName() {
        return accountCategoryName;
    }

    public void setAccountCategoryName(String accountCategoryName) {
        this.accountCategoryName = accountCategoryName;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}

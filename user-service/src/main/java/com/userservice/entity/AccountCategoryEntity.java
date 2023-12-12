package com.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @OneToMany(mappedBy = "accountCategory")
    private Set<UserEntity> userEntities;

    public AccountCategoryEntity() {
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
    @JsonIgnore
    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }
    @JsonIgnore
    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}

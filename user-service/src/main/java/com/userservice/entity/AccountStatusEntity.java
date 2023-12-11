package com.userservice.entity;

import jakarta.persistence.*;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.processing.Generated;
import java.util.Set;

@Entity(name = "account_status")
public class AccountStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account_status_name")
    private String accountStatusName;
    @OneToMany(mappedBy = "accountStatus")
    private Set<UserEntity> userEntities;

    public AccountStatusEntity() {
    }

    public AccountStatusEntity(Integer id, String accountStatusName, Set<UserEntity> userEntities) {
        this.id = id;
        this.accountStatusName = accountStatusName;
        this.userEntities = userEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountStatusName() {
        return accountStatusName;
    }

    public void setAccountStatusName(String accountStatusName) {
        this.accountStatusName = accountStatusName;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}

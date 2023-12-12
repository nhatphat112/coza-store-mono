package com.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.processing.Generated;
import java.util.Set;

@Entity(name = "account_status")
public class AccountStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String accountStatusName;
    @JsonIgnore()
    @OneToMany(mappedBy = "accountStatus")
    private Set<UserEntity> userEntities;

    public AccountStatusEntity() {
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
    @JsonIgnore

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }
    @JsonIgnore

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}

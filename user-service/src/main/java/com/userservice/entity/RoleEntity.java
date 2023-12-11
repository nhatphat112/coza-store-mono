package com.userservice.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "role")
    private Set<UserEntity> userEntities;

    public RoleEntity() {
    }

    public RoleEntity(Integer id, String roleName, String description, Set<UserEntity> userEntities) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.userEntities = userEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}

package com.eproject.data.model.usermodel;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
public class RoleEntity {
    @Id
    @GeneratedValue
    @Column(name = "role_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID roleId;

    @Column(name = "role_name")
    public String roleName;

    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

    public RoleEntity(){}
}
package com.eproject.data.usermodel;

import com.eproject.data.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "users", schema = "eproject")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"roles", "authorities"})
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "full_name")
    private String fullName;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<RoleEntity> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BrandEntity brand;

    public UserEntity(String email, String phoneNumber, String fullName, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.password = password;
    }

    public UserEntity(String email, String phoneNumber, String fullName, String password, Set<RoleEntity> roles) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.password = password;
        this.roles = roles;
    }


    public UserEntity(String email, String phoneNumber, String fullName, String password, Set<RoleEntity> roles, BrandEntity brand) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.password = password;
        this.roles = roles;
        this.brand = brand;
        this.brand.setUser(this);
    }

    public UserEntity(String email, String phoneNumber, String fullName, String password, BrandEntity brand) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.password = password;
        this.brand = brand;
        this.brand.setUser(this);
    }

    public void setUserRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}

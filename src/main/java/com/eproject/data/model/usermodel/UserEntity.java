package com.eproject.data.model.usermodel;

import com.eproject.data.model.BaseEntity;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "users")
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
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email must be a valid email address")
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "Phone must be a valid phone number")
    private String phoneNumber;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<RoleEntity> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BrandEntity brand;

    public UserEntity(String email, String phone, String fullName, String encoded) {

        this.email = email;
        this.phoneNumber = phone;
        this.fullName = fullName;
        this.password = encoded;
    }

    public void setUserRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}

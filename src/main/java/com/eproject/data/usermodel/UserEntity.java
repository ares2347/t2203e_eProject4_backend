package com.eproject.data.usermodel;

import com.eproject.data.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID userId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type", updatable = false, nullable = false)
    private UserTypeEnum userType;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "full_name")
    private String fullName;

    private String password;
}

package com.eproject.data.usermodel;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDto {
    public UUID userId;
    public String email;
    public String phoneNumber;
    public String fullName;
    public Set<String> roles;

    public UserDto(UserEntity user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.fullName = user.getFullName();
        this.roles = user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet());
    }

    public UserDto() {
    }
}

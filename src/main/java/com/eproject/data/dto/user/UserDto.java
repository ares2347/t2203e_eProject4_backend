package com.eproject.data.dto.user;

import com.eproject.data.model.usermodel.RoleEntity;
import com.eproject.data.model.usermodel.UserEntity;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDto {
    public UUID userId;
    public String email;
    public String phoneNumber;
    public String fullName;
    public Set<String> roles;
}

package com.eproject.service.user;

import com.eproject.data.dto.user.UserDto;
import com.eproject.data.model.usermodel.RoleEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.webapi.admincontroller.CreateBrandAccountRequest;
import com.eproject.webapi.authcontroller.RegisterRequest;

import java.util.Set;

public interface IUserService {
    UserEntity addNewUser(RegisterRequest request, Set<RoleEntity> roles);
    UserEntity getUserByUsername(String username);
    UserDto getCurrentUserInfo();
}

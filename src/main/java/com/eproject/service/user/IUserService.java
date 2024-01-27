package com.eproject.service.user;

import com.eproject.data.usermodel.UserEntity;
import com.eproject.webapi.admincontroller.CreateBrandAccountRequest;
import com.eproject.webapi.authcontroller.RegisterRequest;

public interface IUserService {
    UserEntity addNewUser(RegisterRequest request);
    UserEntity addNewBrand(CreateBrandAccountRequest request);
    UserEntity getUserByUsername(String username);
}

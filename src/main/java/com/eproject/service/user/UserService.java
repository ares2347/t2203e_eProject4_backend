package com.eproject.service.user;

import com.eproject.data.usermodel.BrandEntity;
import com.eproject.data.usermodel.RoleEntity;
import com.eproject.data.usermodel.UserEntity;
import com.eproject.data.usermodel.UserRolesEnum;
import com.eproject.repository.user.IBrandRepository;
import com.eproject.repository.user.IUserRepository;
import com.eproject.webapi.admincontroller.CreateBrandAccountRequest;
import com.eproject.webapi.authcontroller.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

@Service
public class UserService implements  IUserService {
    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private BCryptPasswordEncoder _passwordEncoder;

    @Override
    public UserEntity addNewUser(RegisterRequest request) {
        String encoded = _passwordEncoder.encode(request.password);
        UserEntity user = new UserEntity(
                request.email,
                request.phone,
                request.fullName,
                encoded
        );
        user.setUserRoles(new HashSet<RoleEntity>(Collections.singletonList(UserRolesEnum.USER)));
        return _userRepository.save(user);
    }

    @Override
    public UserEntity addNewBrand(CreateBrandAccountRequest request) {
        String defaultPassword = "password";
        String encoded = _passwordEncoder.encode(defaultPassword);
        UserEntity user = new UserEntity(
                request.email,
                request.phone,
                request.name,
                encoded,
                new BrandEntity(request.name)
        );
        user.setUserRoles(new HashSet<RoleEntity>(Collections.singletonList(UserRolesEnum.BRAND)));
        return _userRepository.save(user);
    }
}

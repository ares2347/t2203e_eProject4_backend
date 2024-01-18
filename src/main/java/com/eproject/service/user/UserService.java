package com.eproject.service.user;

import com.eproject.data.usermodel.UserEntity;
import com.eproject.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements  IUserService {
    @Autowired
    private IUserRepository _userRepository;
}

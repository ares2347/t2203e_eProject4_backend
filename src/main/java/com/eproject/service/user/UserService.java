package com.eproject.service.user;

import com.eproject.data.dto.user.UserDto;
import com.eproject.data.model.usermodel.RoleEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.repository.RoleRepository;
import com.eproject.repository.UserRepository;
import com.eproject.service.auth.JwtService;
import com.eproject.service.mail.MailService;
import com.eproject.webapi.authcontroller.RegisterRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private RoleRepository _roleRepository;
    @Autowired
    private BCryptPasswordEncoder _passwordEncoder;
    @Autowired
    private ModelMapper _modelMapper;
    @Autowired
    private JwtService _jwtService;
    @Autowired
    MailService _mailService;

    @Override
    public UserEntity addNewUser(RegisterRequest request, Set<RoleEntity> roles) {
        String encoded = _passwordEncoder.encode(request.password);
        Set<RoleEntity> roleEntities = _roleRepository.getAllByRoleNameIn(roles.stream().map(RoleEntity::getRoleName).collect(Collectors.toSet()));
        UserEntity user = new UserEntity(
                request.email,
                request.phone,
                request.fullName,
                encoded
        );
        user.setUserRoles(roleEntities);
        UserEntity res = _userRepository.saveAndFlush(user);
        _mailService.sendMail(user.getEmail(), "Test email", "Test email content");
        return res;
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return _userRepository.findFirstByEmailOrPhoneNumber(username, "");
    }

    @Override
    public UserDto getCurrentUserInfo() {
        UserEntity user = _jwtService.getCurrentUser();
        return _modelMapper.map(user, UserDto.class);
    }
}

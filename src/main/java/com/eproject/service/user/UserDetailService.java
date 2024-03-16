package com.eproject.service.user;

import com.eproject.data.model.usermodel.UserDetail;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByEmailOrPhoneNumber(identifier, identifier);
        return new UserDetail(user);
    }
}
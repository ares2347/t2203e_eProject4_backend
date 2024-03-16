package com.eproject.config;

import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<UserEntity> {

    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        UserEntity user;
        if (credentials == null || credentials == "") {
            user = null;
        } else {
            user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        }
        return Optional.ofNullable(user);
    }
}
package com.eproject.repository;

import com.eproject.data.model.usermodel.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    UserEntity findFirstByEmailOrPhoneNumber(String email, String phoneNumber);
}

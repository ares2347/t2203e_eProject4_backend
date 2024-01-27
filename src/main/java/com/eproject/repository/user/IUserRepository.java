package com.eproject.repository.user;

import com.eproject.data.usermodel.UserEntity;
import com.eproject.repository.IBaseRepository;

public interface IUserRepository extends IBaseRepository<UserEntity> {
    public UserEntity findByEmailOrPhoneNumber(String email, String phoneNumber);
}

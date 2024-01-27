package com.eproject.repository.user;

import com.eproject.data.usermodel.BrandEntity;
import com.eproject.data.usermodel.UserEntity;
import com.eproject.repository.IBaseRepository;

public interface IBrandRepository  extends IBaseRepository<BrandEntity> {
    public BrandEntity findBrandEntityByUser(UserEntity user);
}

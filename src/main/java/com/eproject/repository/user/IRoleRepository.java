package com.eproject.repository.user;

import com.eproject.data.usermodel.RoleEntity;
import com.eproject.data.usermodel.UserEntity;
import com.eproject.repository.IBaseRepository;

public interface IRoleRepository  extends IBaseRepository<RoleEntity> {
    public RoleEntity findRoleEntitiesByName(String name);
}

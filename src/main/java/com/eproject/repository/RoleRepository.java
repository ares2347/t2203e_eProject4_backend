package com.eproject.repository;

import com.eproject.data.model.referencemodel.ReferenceDataEntity;
import com.eproject.data.model.usermodel.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Set<RoleEntity> getAllByRoleNameIn(Collection<String> roleName);
}

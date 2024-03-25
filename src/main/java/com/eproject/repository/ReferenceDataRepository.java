package com.eproject.repository;

import com.eproject.data.model.referencemodel.ReferenceDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReferenceDataRepository extends JpaRepository<ReferenceDataEntity, UUID> {
    List<ReferenceDataEntity> findAllByReferenceDataType(String referenceDataType);
}

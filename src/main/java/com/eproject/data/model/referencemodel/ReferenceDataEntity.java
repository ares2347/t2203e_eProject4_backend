package com.eproject.data.model.referencemodel;

import com.eproject.data.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "reference_data")
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDataEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "reference_data_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID referenceDataId;

    @Column(name = "reference_data_type", nullable = false)
    private String referenceDataType;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_description", nullable = false)
    private String codeDescription;

    @Column(name = "parameter_data")
    private String parameterData;


}

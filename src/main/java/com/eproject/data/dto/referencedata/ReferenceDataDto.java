package com.eproject.data.dto.referencedata;

import com.eproject.data.model.referencemodel.ReferenceDataEntity;
import jakarta.persistence.Column;

public class ReferenceDataDto {
    public String referenceDataType;
    public String code;
    public String codeName;
    public String codeDescription;
    public String parameterData;

    public ReferenceDataDto(ReferenceDataEntity entity) {
        this.referenceDataType = entity.getReferenceDataType();
        this.code = entity.getCode();
        this.codeName = entity.getCodeName();
        this.codeDescription = entity.getCodeDescription();
        this.parameterData = entity.getParameterData();
    }
}

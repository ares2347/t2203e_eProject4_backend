package com.eproject.service.referencedata;

import com.eproject.data.dto.referencedata.ReferenceDataDto;

import java.util.List;

public interface IReferenceDataService {
    List<ReferenceDataDto> getReferenceDataByType(String referenceDataType);
}

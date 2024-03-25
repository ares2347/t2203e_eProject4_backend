package com.eproject.service.referencedata;

import com.eproject.data.dto.referencedata.ReferenceDataDto;
import com.eproject.repository.ReferenceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceDataService implements IReferenceDataService{
    @Autowired
    ReferenceDataRepository _referenceDataRepository;
    @Override
    public List<ReferenceDataDto> getReferenceDataByType(String referenceDataType) {
        return
                _referenceDataRepository.findAllByReferenceDataType(referenceDataType)
                        .stream().map(ReferenceDataDto::new).toList();
    }
}

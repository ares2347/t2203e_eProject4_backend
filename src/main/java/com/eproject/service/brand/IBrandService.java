package com.eproject.service.brand;

import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.webapi.admincontroller.CreateBrandAccountRequest;
import org.springframework.stereotype.Service;

@Service
public interface IBrandService {
    BrandEntity updateBrand();
    BrandEntity addNewBrand(CreateBrandAccountRequest request);
}

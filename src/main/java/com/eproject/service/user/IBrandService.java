package com.eproject.service.user;

import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.webapi.admincontroller.CreateBrandAccountRequest;

public interface IBrandService {
    BrandEntity updateBrand();
    BrandEntity addNewBrand(CreateBrandAccountRequest request);
}

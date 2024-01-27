package com.eproject.service.user;

import com.eproject.data.usermodel.BrandEntity;
import com.eproject.repository.user.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements IBrandService {
    @Autowired
    IBrandRepository _brandRepository;
    @Override
    public BrandEntity updateBrand() {
        return null;
    }

    @Override
    public BrandEntity addBrand() {
        return null;
    }
}

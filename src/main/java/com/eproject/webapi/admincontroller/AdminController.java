package com.eproject.webapi.admincontroller;

import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.service.brand.BrandService;
import com.eproject.service.brand.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private BrandService _brandService;


    @PostMapping(path = "/create-brand", consumes = "application/json", produces = "application/json")
    public ResponseEntity createBrand(@RequestBody CreateBrandAccountRequest request) {
        try {
            BrandEntity brand = _brandService.addNewBrand(request);
            return new ResponseEntity(brand, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
}

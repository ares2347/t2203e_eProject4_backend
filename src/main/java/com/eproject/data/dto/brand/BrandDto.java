package com.eproject.data.dto.brand;

import com.eproject.data.model.brandmodel.BrandEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public class BrandDto {
    public UUID brandId;

    public String brandName;

    public String hotline;

    public String email;

    public String landingPage;

    public String thumbnailUrl;

    public BrandDto(BrandEntity entity) {
        this.brandId = entity.getBrandId();
        this.brandName = entity.getBrandName();
        this.hotline = entity.getHotline();
        this.email = entity.getEmail();
        this.landingPage = entity.getLandingPage();
        this.thumbnailUrl = entity.getThumbnailUrl();
    }
}

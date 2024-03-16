package com.eproject.data.dto.brand;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public class BrandDto {
    private UUID brandId;

    private String brandName;

    private String hotline;

    private String email;

    private String landingPage;

    private String thumbnailUrl;
}

package com.eproject.data.model.brandmodel;

import com.eproject.data.model.BaseEntity;
import com.eproject.data.model.tripmodel.RouteEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "brand_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID brandId;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "hotline", nullable = false)
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "Hotline must be a valid phone number")
    private String hotline;

    @Column(name = "email", unique = true, nullable = false)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email must be a valid email address")
    private String email;

    @Column(name = "landing_page")
    private String landingPage;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<UserEntity> users;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<RouteEntity> routes;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<VehicleEntity> vehicles;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<DriverEntity> drivers;

    public BrandEntity(String brandName, String hotline, String email) {

        this.brandName = brandName;
        this.hotline = hotline;
        this.email = email;
    }
}

package com.eproject.data.usermodel;

import com.eproject.data.BaseEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "brands", schema = "eproject")
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "brand_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID brandId;

    @Column(name = "name")
    private String name;

    @Column(name = "hotline")
    private String hotline;

    @Column(name = "landing_page")
    private String landingPage;

    @OneToMany(mappedBy = "brand")
    List<VehicleConfigEntity> vehicleConfigs;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public BrandEntity(String name) {
        this.name = name;
    }
}

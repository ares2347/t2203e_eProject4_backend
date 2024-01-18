package com.eproject.data.vehiclemodel;

import com.eproject.data.BaseEntity;
import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.usermodel.BrandEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "vehicle_configs")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleConfigEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "vehicle_config_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID vehicleConfigId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vehicle_type")
    private VehicleTypeEnum vehicleType;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name ="seat_amount")
    private int seatAmount;

    @Column(name = "seat_config")
    private String seatConfig;

    @OneToMany(mappedBy = "vehicleConfig")
    List<TripConfigEntity> tripConfigs;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private BrandEntity brand;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "image_urls")
    private String imageUrls;
}

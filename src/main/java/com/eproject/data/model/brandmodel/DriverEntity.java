package com.eproject.data.model.brandmodel;

import com.eproject.data.model.BaseEntity;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import com.eproject.webapi.brandcontroller.CreateDriverRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "drivers")
@NoArgsConstructor
@AllArgsConstructor
public class DriverEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "driver_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID driverId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "dob", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate dob;

    @Column(name = "phone_number", nullable = false)
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "Phone must be a valid phone number")
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}",
            message = "Email must be a valid email address")
    private String email;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "address")
    private String address;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private VehicleEntity vehicle;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BrandEntity brand;

    public DriverEntity(CreateDriverRequest request) {
        this.fullName = request.fullName;
        this.dob = request.dob;
        this.phoneNumber = request.phoneNumber;
        this.email = request.email;
        this.nationalId = request.nationalId;
        this.address = request.address;
        this.photoUrl = request.photoUrl;
    }
}

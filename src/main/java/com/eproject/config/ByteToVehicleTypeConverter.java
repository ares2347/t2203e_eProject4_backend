package com.eproject.config;

import com.eproject.data.vehiclemodel.VehicleTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.UUID;

@Component
public class ByteToVehicleTypeConverter implements Converter<Byte, VehicleTypeEnum> {
    @Override
    public VehicleTypeEnum convert(Byte source) {
        return VehicleTypeEnum.values()[(Integer.valueOf(source))];
    }
}


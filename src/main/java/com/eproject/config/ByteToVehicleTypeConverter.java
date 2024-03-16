package com.eproject.config;

import com.eproject.data.model.vehiclemodel.VehicleTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ByteToVehicleTypeConverter implements Converter<Byte, VehicleTypeEnum> {
    @Override
    public VehicleTypeEnum convert(Byte source) {
        return VehicleTypeEnum.values()[(Integer.valueOf(source))];
    }
}


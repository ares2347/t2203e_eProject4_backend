package com.eproject.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.UUID;

@Component
public class BinaryToUuidConverter implements Converter<byte[], UUID> {
    @Override
    public UUID convert(byte[] source) {
        if(source == null || source.length == 0)
            return UUID.fromString("00000000-0000-0000-0000-000000000000");
        ByteBuffer bb = ByteBuffer.wrap(source);
        long high = bb.getLong();
        long low = bb.getLong();
        return new UUID(high, low);
    }
}

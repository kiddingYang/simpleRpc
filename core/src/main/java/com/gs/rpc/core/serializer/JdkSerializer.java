package com.gs.rpc.core.serializer;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class JdkSerializer implements Serializer {


    @Override
    public byte[] serialize(Object obj) {
        return SerializationUtils.serialize((Serializable) obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Object object = SerializationUtils.deserialize(bytes);
        return (T) object;
    }
}

package com.gs.rpc.core.codec;

import com.gs.rpc.core.serializer.JdkSerializer;
import com.gs.rpc.core.serializer.Serializer;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class ProtocolDecoder extends LengthFieldBasedFrameDecoder {

    private Serializer serializer;

    private Class clazz;

    private static int maxFrameLength = 8 * 1024 * 1024;

    public ProtocolDecoder(Class clazz) {
        this(new JdkSerializer(), clazz);
    }

    public ProtocolDecoder(Serializer serializer, Class clazz) {
        super(maxFrameLength, 0, 4, 0, 4);
        this.serializer = serializer;
        this.clazz = clazz;
    }


}

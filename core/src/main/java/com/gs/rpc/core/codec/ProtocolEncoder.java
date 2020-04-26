package com.gs.rpc.core.codec;

import com.gs.rpc.core.serializer.JdkSerializer;
import com.gs.rpc.core.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ProtocolEncoder extends MessageToByteEncoder<Object> {

    private Serializer serializer;

    public ProtocolEncoder() {
        this.serializer = new JdkSerializer();
    }

    public ProtocolEncoder(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        byte[] bytes = serializer.serialize(msg);
        int length = bytes.length;
        out.writeInt(length);
        out.writeBytes(bytes);
    }
}

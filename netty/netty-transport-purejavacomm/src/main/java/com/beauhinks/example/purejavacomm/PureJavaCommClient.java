package com.beauhinks.example.purejavacomm;

import com.beauhinks.purejavacomm.PureJavaCommChannel;
import com.beauhinks.purejavacomm.PureJavaCommDeviceAddress;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import purejavacomm.CommPortIdentifier;

import java.util.Enumeration;
import java.util.List;


/**
 * Sends one message to a serial device
 */
public final class PureJavaCommClient {

    public static void main(String[] args) throws Exception {
        CommPortIdentifier portid = null;
        Enumeration e = CommPortIdentifier.getPortIdentifiers();
        while (e.hasMoreElements()) {
            portid = (CommPortIdentifier) e.nextElement();
            System.out.println("found " + portid.getName());
        }
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(PureJavaCommChannel.class)
                    .handler(new ChannelInitializer<PureJavaCommChannel>() {
                        @Override
                        public void initChannel(PureJavaCommChannel ch) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("&#&".getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new MessageToMessageCodec<String, String>() {
                                @Override
                                protected void encode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) throws Exception {
                                    System.out.println("encode " + s);
                                }

                                @Override
                                protected void decode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) throws Exception {
                                    System.out.println("decode " + s);
                                }
                            });
                        }
                    });

            ChannelFuture f = b.connect(new PureJavaCommDeviceAddress("COM4")).sync();
            
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    private PureJavaCommClient() {
    }
}
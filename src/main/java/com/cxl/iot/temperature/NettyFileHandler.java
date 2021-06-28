package com.cxl.iot.temperature;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class NettyFileHandler extends SimpleChannelInboundHandler<String> {

    private static final String TEMPERATURE_FILE = "temperature";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server receive message: " + msg);
        if (TEMPERATURE_FILE.equals(msg)) {
            getFile(ctx);
        } else {
            error(ctx);
        }
    }

    private void getFile(ChannelHandlerContext ctx) throws IOException {
        String filePath = Config.getPropertiesConfig("file.temperature.path");
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(filePath, "r");
            ctx.write(new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length()));
            ctx.writeAndFlush("\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ctx.writeAndFlush("code:-1; read temperature error! \n");
        }
    }

    private void error(ChannelHandlerContext ctx) {
        ctx.write("not support command");
        ctx.writeAndFlush("\n");
    }
}

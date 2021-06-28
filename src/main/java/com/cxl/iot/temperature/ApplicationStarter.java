package com.cxl.iot.temperature;

public class ApplicationStarter {

    public static void main(String[] args) throws Exception {
        String host = Config.getPropertiesConfig("server.host");
        if(host==null || host.equals("")){
            throw new IllegalArgumentException("server.host error current is "+host);
        }
        String portStr = Config.getPropertiesConfig("server.port");
        int port = Integer.parseInt(portStr);

        NettyFileServer fileServer = new NettyFileServer();
        fileServer.server(host,port);
    }

}

package com.iokays.h2;

import org.h2.tools.Server;

public class H2Server {
    public static void main(String[] args) throws Exception {
        // 启动 H2 TCP 服务器，默认端口 9092
        Server tcpServer = Server.createTcpServer("-tcpAllowOthers",
                "-tcpPort", "9092",
                "-ifNotExists"  // 允许不存在时创建
        ).start();
        System.out.println("H2 TCP Server started at " + tcpServer.getURL());

        // 启动 H2 Web 服务器，默认端口 8082
        Server webServer = Server.createWebServer(
                "-webAllowOthers",
                "-webPort", "8082",
                "-ifNotExists"  // 允许不存在时创建
        ).start();
        System.out.println("H2 Web Server started at http://localhost:" + webServer.getPort());

        // 保持程序运行
        System.out.println("Press Ctrl+C to stop the server");
        Thread.currentThread().join();
    }
}
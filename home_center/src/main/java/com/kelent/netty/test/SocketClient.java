package com.kelent.netty.test;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * Author : syl
 * datetime ：2018/7/25.
 */
public class SocketClient {

    public static void main(String[] args) throws Exception{
        String host = "127.0.0.1";
        int port = 9000;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        SocketClientRun socketClientRun = new SocketClientRun(socket) ;
        new Thread(socketClientRun).start();

        SocketClientWriteRun socketClientWriteRun = new SocketClientWriteRun(socket.getOutputStream()) ;
        new Thread(socketClientWriteRun).start();
        Thread.sleep(10000);
    }


}

package com.wwl.can.chartroom.thread;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerThread implements Runnable {
    private android.os.Handler handler;
    private String localIP;
    private int port;
    private Boolean openThread = true;
    private ServerSocket serverSocket;
    public TcpServerThread(Handler handler,String localIP, int port) {
        this.handler = handler;
        this.localIP = localIP;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (openThread){
                Socket socket = serverSocket.accept();//阻塞方法

                //接受消息，并且把消息handler给本机
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null){
                    sb.append(line + "\r\n");
                }
                Message message = Message.obtain();
                String ip = socket.getInetAddress().getHostAddress();
                if (ip.equals(localIP)){
                    message.what = 0;
                }else {
                    message.what = 1;
                }
                String news = sb.toString();
                String msg = news.substring(0,news.length() - 2);
                message.obj = msg;
                handler.sendMessage(message);

                //返回消息，告诉客户端受到消息了
//                OutputStream outputStream = socket.getOutputStream();
//                outputStream.write("接受到了消息".getBytes());

                socket.close();

            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopThread(){
        this.openThread = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

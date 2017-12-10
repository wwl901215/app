package com.wwl.can.chartroom.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.wwl.can.chartroom.fragment.ChartRoomFragment;
import com.wwl.can.learn.wifi.GetIpAddressMethod;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerThread implements Runnable {
    private android.os.Handler handler;
    private String localIP;
    private int port;
    public TcpServerThread(Handler handler,String localIP, int port) {
        this.handler = handler;
        this.localIP = localIP;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();//阻塞方法
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = null;
                while ((line = br.readLine()) != null){
                    Log.e("tcpserver log:",line);
                    Message message = Message.obtain();

                    String ip = socket.getInetAddress().getHostAddress();
                    if (ip.equals(localIP)){
                        message.what = 1;
                    }else {
                        message.what = 0;
                    }
                    message.obj = line;
                    handler.sendMessage(message);
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

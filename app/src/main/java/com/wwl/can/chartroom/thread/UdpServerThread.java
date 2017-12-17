package com.wwl.can.chartroom.thread;

import android.os.Handler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by wangwenliang on 2017/12/14.
 */

public class UdpServerThread extends Thread {

    private int port;
    private String  localID;
    private Handler handler;


    public  UdpServerThread(Handler handler, String localID, int port){
        this.handler = handler;
        this.localID = localID;
        this.port = port;
    }


    @Override
    public void run() {
        super.run();

        try {
            DatagramSocket socket = new DatagramSocket(port);

            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length);

            while (true){
                socket.receive(packet);//阻塞方法
                String info = new String(bytes,0,packet.getLength());
//                while ()
//                handler
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package com.wwl.can.learn.wifi;

import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by wangwenliang on 2017/12/4.
 */

public class GetIpAddress {
    public static String IP;
    public static int PORT;

    public static String getIP() {
        return IP;
    }

    public static int getPORT() {
        return PORT;
    }

    public static void getLocalIpAddress(ServerSocket serverSocket) {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    String mIP = inetAddress.getHostAddress().substring(0, 3);
                    if ((mIP.equals("192"))) {
                        IP = inetAddress.getHostAddress();
                        PORT = serverSocket.getLocalPort();
                        Log.e("ip:", IP + "");
                        Log.e("port", "" + PORT + "");
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}

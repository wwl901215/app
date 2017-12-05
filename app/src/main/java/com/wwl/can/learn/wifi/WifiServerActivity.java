package com.wwl.can.learn.wifi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wwl.can.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WifiServerActivity extends AppCompatActivity {

    @Bind(R.id.tv_serveip) TextView tvServeip;
    @Bind(R.id.tv_serve_reveived_data) TextView tvServeReveivedData;

    private ServerSocket serverSocket = null;
    private StringBuffer stringBuffer;
    private InputStream inputStream;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvServeip.setText(msg.obj.toString());
                    break;
                case 2:
                    tvServeReveivedData.setText(msg.obj.toString());
                    stringBuffer.setLength(0);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_server);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        stringBuffer = new StringBuffer();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    serverSocket = new ServerSocket(8001);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                GetIpAddress.getLocalIpAddress(serverSocket);

                Message mes = handler.obtainMessage();
                mes.what = 1;
                mes.obj = "IP:" +  GetIpAddress.getIP() + " PORT:" + GetIpAddress.getPORT();
                handler.sendMessage(mes);

                while (true){
                    Socket socket = null;
                    try {
                        socket = serverSocket.accept();
                        inputStream = socket.getInputStream();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    new ServerThread(socket,inputStream).start();
                }



            }
        };
        thread.start();

    }


    class ServerThread extends Thread{
        private Socket socket;
        private InputStream inputStream;
        private StringBuffer stringBuffer = WifiServerActivity.this.stringBuffer;

        public ServerThread(Socket socket,InputStream inputStream){
            this.socket = socket;
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            int len;
            byte[] bytes = new byte[20];
            boolean isString = false;

            try {

                while ((len = inputStream.read(bytes)) != -1){
                    for (int i = 0; i < len; i++) {
                        if (bytes[i] != '\0'){
                            stringBuffer.append((char) bytes[i]);
                        }else {
                            isString = true;
                            break;
                        }
                    }
                    if (isString){
                        Message message = handler.obtainMessage();
                        message.what = 2;
                        message.obj = stringBuffer;
                        handler.sendMessage(message);
                        isString = false;
                    }
                }

            } catch (IOException e){
                e.printStackTrace();
                try {
                    inputStream.close();
                    socket.close();
                } catch (IOException e1){
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

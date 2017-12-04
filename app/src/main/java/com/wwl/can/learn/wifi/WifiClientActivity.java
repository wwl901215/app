package com.wwl.can.learn.wifi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wwl.can.R;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WifiClientActivity extends AppCompatActivity {

    @Bind(R.id.et_connect) EditText etConnect;
    @Bind(R.id.bt_connect) Button btConnect;
    @Bind(R.id.et_send) EditText etSend;
    @Bind(R.id.tb_send) Button tbSend;

    private OutputStream outputStream = null;
    private Socket socket = null;
    private String ip;
    private String data;
    private boolean socketStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_client);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
    }

    public void connect(){
        ip = etConnect.getText().toString();
        if (ip == null){
            Toast.makeText(WifiClientActivity.this,"please input server ip",Toast.LENGTH_SHORT).show();
        }

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                if (!socketStatus) {
                    try {
                        socket = new Socket(ip,8001);
                        if (socket == null){

                        }else {
                            socketStatus = true;
                        }
                        outputStream = socket.getOutputStream();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            }
        };
        thread.start();

    }


    public void send(){
        data = etSend.getText().toString();
        if (data == null){
            Toast.makeText(WifiClientActivity.this,"please input sending data", Toast.LENGTH_SHORT).show();
        }else {
            data = data + '\0';
        }

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                if (socketStatus){
                    try{
                        outputStream.write(data.getBytes());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    @OnClick({R.id.bt_connect, R.id.tb_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_connect:
                this.connect();
                break;
            case R.id.tb_send:
                this.send();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            outputStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

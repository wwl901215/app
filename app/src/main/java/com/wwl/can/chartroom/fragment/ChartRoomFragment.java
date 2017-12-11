package com.wwl.can.chartroom.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.wwl.can.R;
import com.wwl.can.chartroom.adapter.ChartRoomAdapter;
import com.wwl.can.chartroom.bean.ChartRoomItemBean;
import com.wwl.can.chartroom.thread.TcpServerThread;
import com.wwl.can.learn.wifi.GetIpAddressMethod;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangwenliang on 2017/12/7.
 */

public class ChartRoomFragment extends Fragment {

    @Bind(R.id.lv_chartroom) ListView lvChartroom;
    @Bind(R.id.et_chartroom) EditText etChartroom;
    @Bind(R.id.bt_send) Button btSend;
    @Bind(R.id.iv_chartroom_upload_pic) ImageView ivChartroomUploadPic;
    @Bind(R.id.iv_chartroom_send_voice) ImageView ivChartroomSendVoice;
    private List<ChartRoomItemBean> data;
    private ChartRoomAdapter adapter;
    private Handler handler;
    private Handler serverHander;
    private Context context;
    private String ip;
    private int port;
    private Thread sThread;
    private TcpServerThread serverThread;

    public void setContext(Context context,String ip,int port){
        this.context = context;
        this.ip = ip;
        this.port = port;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_room_fragment, container, false);
        ButterKnife.bind(this, view);
        iniData();
        iniView();
        return view;
    }


    private void iniData() {
        data = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            ChartRoomItemBean bean = new ChartRoomItemBean();
            bean.setNickname("涛涛："+i);
            bean.setChartcontent("我是不是很帅？");
            bean.setType(0);
            data.add(bean);
        }
        for (int i = 0; i < 1; i++) {
            ChartRoomItemBean bean1 = new ChartRoomItemBean();
            bean1.setNickname("：曼曼"+i);
            bean1.setChartcontent("说话之前先照照镜子，另外，别忘了给镜子买个意外险");
            bean1.setType(1);
            data.add(bean1);
        }
    }

    @SuppressLint("HandlerLeak")
    private void iniView() {
        adapter = new ChartRoomAdapter(ChartRoomFragment.this.getContext(),data);
        lvChartroom.setAdapter(adapter);

        //用于接受服务socket返回的消息
        serverHander = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                ChartRoomItemBean bean = new ChartRoomItemBean();
                bean.setChartcontent((String) msg.obj);
                if (msg.what == 0){
                    bean.setNickname("me");
                    bean.setType(0);
                }else {
                    bean.setNickname("you");
                    bean.setType(1);
                }
                data.add(bean);
                adapter.notifyDataSetChanged();
            }
        };
        //启动服务线程，打开serversocket
        serverThread = new TcpServerThread(serverHander,GetIpAddressMethod.getIpAddress(context),port);
        sThread = new Thread( serverThread);
        sThread.start();


        //客户端socket
        HandlerThread thread = new HandlerThread("handerthread");
        thread.start();
        handler = new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
               if (msg.what == 1){
                   String textmsg = (String) msg.obj;
//                   String ip = GetIpAddressMethod.getIpAddress(ChartRoomFragment.this.getContext());
                   try {
                       Socket socket = new Socket(ip,port);
                       //发送给对方
                       OutputStream outputStream = socket.getOutputStream();
                       outputStream.write(textmsg.getBytes());
                       //接受对方返回的消息
//                       BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                       StringBuffer sb = new StringBuffer();
//                       String line = null;
//                       while ((line = br.readLine()) != null){
//                           sb.append(line);
//                       }
//                       String news = sb.toString();
//                       String msgFromServer = news.substring(0,news.length() - 2);

                       socket.close();
//
//                       ChartRoomItemBean bean = new ChartRoomItemBean();
//                       bean.setNickname("me");
//                       bean.setChartcontent(msgFromServer);
//                       bean.setType(0);
//                       adapter.notifyDataSetChanged();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }

            }
        };

    }

    @OnClick({R.id.bt_send, R.id.iv_chartroom_upload_pic, R.id.iv_chartroom_send_voice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_send:
                this.send();
                break;
            case R.id.iv_chartroom_upload_pic:
                break;
            case R.id.iv_chartroom_send_voice:
                break;
        }
    }

    private void send(){
        Message message = Message.obtain();
        message.what = 1;
        message.obj = etChartroom.getText().toString();
        handler.sendMessage(message);
        etChartroom.setText("");
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null){
            imm.hideSoftInputFromWindow(etChartroom.getWindowToken(),0);
        }
    }


    public void stopThread(){
        if (serverThread != null){
            serverThread.stopThread();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (sThread != null){
            sThread.interrupt();
        }
    }

}

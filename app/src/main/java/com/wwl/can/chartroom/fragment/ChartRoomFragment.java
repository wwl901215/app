package com.wwl.can.chartroom.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.wwl.can.R;
import com.wwl.can.chartroom.adapter.ChartRoomAdapter;
import com.wwl.can.chartroom.bean.ChartRoomItemBean;

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
            bean.setType(1);
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

    private void iniView() {
        adapter = new ChartRoomAdapter(ChartRoomFragment.this.getContext(),data);
        lvChartroom.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bt_send, R.id.iv_chartroom_upload_pic, R.id.iv_chartroom_send_voice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_send:
                ChartRoomItemBean bean = new ChartRoomItemBean();
                bean.setType(2);
                bean.setChartcontent(etChartroom.getText().toString());
                bean.setNickname("亮");
                data.add(bean);
                adapter.notifyDataSetChanged();
                etChartroom.setText("");
                break;
            case R.id.iv_chartroom_upload_pic:
                break;
            case R.id.iv_chartroom_send_voice:
                break;
        }
    }
}

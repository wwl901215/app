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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_room_fragment, container, false);


        ButterKnife.bind(this, view);
        return view;
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
                break;
            case R.id.iv_chartroom_upload_pic:
                break;
            case R.id.iv_chartroom_send_voice:
                break;
        }
    }
}

package com.wwl.can.chartroom.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wwl.can.R;
import com.wwl.can.chartroom.fragment.ChartRoomFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultiChartRoom extends AppCompatActivity {

    @Bind(R.id.tv_top_back) ImageView tvTopBack;
    @Bind(R.id.topbar_nickname) TextView topbarNickname;
    @Bind(R.id.fl_chartroom) FrameLayout flChartroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_chart_room);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        ChartRoomFragment chartRoomFragment = new ChartRoomFragment();
        transaction.add(chartRoomFragment,"CHART");
        transaction.commit();
    }

    @OnClick(R.id.tv_top_back)
    public void onViewClicked() {
        this.finish();
    }
}

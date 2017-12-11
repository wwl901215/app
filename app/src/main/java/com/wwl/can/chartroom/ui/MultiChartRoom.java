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
    private ChartRoomFragment chartRoomFragment;

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
        chartRoomFragment = new ChartRoomFragment();
        chartRoomFragment.setContext(this,
                getIntent().getStringExtra("name"),
                getIntent().getIntExtra("password",8001));
        transaction.add(R.id.fl_chartroom,chartRoomFragment);
        transaction.commit();
    }

    @OnClick(R.id.tv_top_back)
    public void onViewClicked() {
        if (chartRoomFragment != null) chartRoomFragment.stopThread();
        this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (chartRoomFragment != null) chartRoomFragment.stopThread();
        this.finish();
    }
}

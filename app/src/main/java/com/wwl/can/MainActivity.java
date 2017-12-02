package com.wwl.can;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wwl.can.learn.Learn;
import com.wwl.can.wifi.ui.WiFiActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.bt_wifi) Button btWifi;
    @Bind(R.id.bt_bluetooth) Button btBluetooth;
    @Bind(R.id.bt_learn) Button btLearn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_wifi, R.id.bt_bluetooth,R.id.bt_learn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_wifi:
                Intent intent = new Intent(MainActivity.this, WiFiActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_bluetooth:
                break;
            case R.id.bt_learn:
                Intent intent1 = new Intent(MainActivity.this, Learn.class);
                startActivity(intent1);
                break;
        }
    }

}

package com.wwl.can;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wwl.can.canvas.CanvasMenu;
import com.wwl.can.chartroom.ui.LoginActivity;
import com.wwl.can.learn.Learn;
import com.wwl.can.webview.WebViewActivity;
import com.wwl.can.wifi.ui.WiFiActivity;
import com.wwl.can.zhujie.AnnotationActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.bt_wifi)
    Button btWifi;
    @Bind(R.id.bt_bluetooth)
    Button btBluetooth;
    @Bind(R.id.bt_learn)
    Button btLearn;
    @Bind(R.id.bt_chartroom)
    Button btChartroom;
    @Bind(R.id.bt_annotation)
    Button btAnnotation;
    @Bind(R.id.bt_canvas) Button btCanvas;
    @Bind(R.id.bt_webview) Button btWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_wifi, R.id.bt_bluetooth, R.id.bt_learn, R.id.bt_chartroom, R.id.bt_annotation, R.id.bt_canvas, R.id.bt_webview})
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
            case R.id.bt_chartroom:
                Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_annotation:
                Intent intent3 = new Intent(MainActivity.this, AnnotationActivity.class);
                startActivity(intent3);
            case R.id.bt_canvas:
                Intent intent4 = new Intent(MainActivity.this, CanvasMenu.class);
                startActivity(intent4);
            case R.id.bt_webview:
                Intent intent5 = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent5);
        }
    }
}

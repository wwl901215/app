package com.wwl.can.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wwl.can.R;
import com.wwl.can.canvas.canvas1.Canvas1Activity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasMenu extends AppCompatActivity {

    @Bind(R.id.bt_canvas1) Button btCanvas1;
    @Bind(R.id.bt_canvas2) Button btCanvas2;
    @Bind(R.id.bt_canvas3) Button btCanvas3;
    @Bind(R.id.bt_canvas4) Button btCanvas4;
    @Bind(R.id.bt_canvas5) Button btCanvas5;
    @Bind(R.id.bt_canvas6) Button btCanvas6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_canvas1, R.id.bt_canvas2, R.id.bt_canvas3, R.id.bt_canvas4, R.id.bt_canvas5, R.id.bt_canvas6})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.bt_canvas1:
                intent.setClass(CanvasMenu.this, Canvas1Activity.class);
                break;
            case R.id.bt_canvas2:
                break;
            case R.id.bt_canvas3:
                break;
            case R.id.bt_canvas4:
                break;
            case R.id.bt_canvas5:
                break;
            case R.id.bt_canvas6:
                break;
        }
        startActivity(intent);
    }
}

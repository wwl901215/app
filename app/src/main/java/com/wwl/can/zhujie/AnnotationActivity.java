package com.wwl.can.zhujie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wwl.can.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnnotationActivity extends AppCompatActivity {

    @Bind(R.id.et_text) EditText etText;
    @Bind(R.id.btn_setText) Button btnSetText;
    @Bind(R.id.tv_getText) TextView tvGetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        ButterKnife.bind(this);
        QueryGenerator qu = new QueryGenerator();
        String sql = qu.generatQuery(Student.class);
        Log.e("aaaaa", sql);
        Toast.makeText(this, sql, Toast.LENGTH_LONG).show();
        initView();
    }

    private void initView() {
        GoodsDemo demo = new GoodsDemo();
        demo.setImgUrl("说好了的粘性吃的是");
        EventBus.getDefault().postSticky(demo);//粘性通知
        EventBus.getDefault().register(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("hehele","hehesdfew");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GoodsDemo demo = new GoodsDemo();
                demo.setImgUrl("来自子线程的亲切问候");
                EventBus.getDefault().post(demo);
                Log.e("hehele","hehe");
            }
        }).start();
    }

    /**
     * ThreacMode:
     * NAIN UI主线程
     * BACKGROUND 后台线程
     * POSTING 和发布者处在同一个线程
     * ASYNC 异步线程
     *
     * sticky:粘性通知，可以先发通知，再注册方法
     *
     * priority:默认是0，优先级，优先级越高越早获取消息，并且可以通过cancelEventDelivery取消事件的传递（但是必须ThreadMode设置为POSTING才有效）
     *
     * @param data
     */

    @Subscribe(threadMode = ThreadMode.BACKGROUND, priority = 1, sticky = true)
    public void setEtText(GoodsDemo data) {
        tvGetText.setText(data.getImgUrl());
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 200)
    public void setEtText2(GoodsDemo data) {
        Toast.makeText(this, "hehe le", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().cancelEventDelivery(data);//拦截消息
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 300)
    public void setEtText3(GoodsDemo data) {
        tvGetText.setText("ni cai a "+ data.getImgUrl());
    }

    @OnClick(R.id.btn_setText)
    public void onViewClicked() {
        String edtText = etText.getText().toString();
        GoodsDemo demo = new GoodsDemo();
        demo.setImgUrl("通过eventbus获取的图片链接:" + edtText);
        EventBus.getDefault().post(demo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}

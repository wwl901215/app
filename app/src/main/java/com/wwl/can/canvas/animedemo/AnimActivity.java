package com.wwl.can.canvas.animedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wwl.can.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnimActivity extends AppCompatActivity {


    @Bind(R.id.seekbar) SeekBar seekbar;
    @Bind(R.id.tv) TextView tv;
    @Bind(R.id.iv) ImageView iv;
    RotateAnimation rotateAnimation;
    private float pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    tv.setText(String.valueOf(progress));
                    rotateAnimation = new RotateAnimation((pr / 100) * 360, (progress / 100) * 360, 1.0f, 0.5f);
                    rotateAnimation.setDuration(100);
                    rotateAnimation.setFillAfter(true);
                    iv.startAnimation(rotateAnimation);
                    pr = progress;
                } else {
                    tv.setText("自动滑动");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}

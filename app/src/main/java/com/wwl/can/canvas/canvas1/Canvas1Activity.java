package com.wwl.can.canvas.canvas1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.wwl.can.R;

/**
 * http://blog.csdn.net/harvic880925/article/details/38875149
 */
public class Canvas1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas1);
        FrameLayout root = (FrameLayout) findViewById(R.id.root);
        root.addView(new MyView(Canvas1Activity.this));
    }
}

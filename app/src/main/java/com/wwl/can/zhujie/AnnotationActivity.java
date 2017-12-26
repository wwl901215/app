package com.wwl.can.zhujie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wwl.can.R;

public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        QueryGenerator qu = new QueryGenerator();
        String sql = qu.generatQuery(Student.class);
        Log.e("aaaaa",sql);
        Toast.makeText(this,sql,Toast.LENGTH_LONG).show();
    }
}

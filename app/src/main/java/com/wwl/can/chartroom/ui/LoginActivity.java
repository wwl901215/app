package com.wwl.can.chartroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wwl.can.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.et_login_name) EditText etLoginName;
    @Bind(R.id.et_login_pw) EditText etLoginPw;
    @Bind(R.id.bt_sure) Button btSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_sure)
    public void onViewClicked() {
        String name = etLoginName.getText().toString();
        String pd = etLoginPw.getText().toString();
        if (name.isEmpty() || pd.isEmpty()){
            Toast.makeText(this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(LoginActivity.this,MultiChartRoom.class);
        intent.putExtra("name",name.trim());
        intent.putExtra("password",pd.trim());
        startActivity(intent);
    }
}

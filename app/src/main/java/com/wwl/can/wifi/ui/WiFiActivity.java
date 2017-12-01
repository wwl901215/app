package com.wwl.can.wifi.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wwl.can.R;
import com.wwl.can.wifi.adapter.WifiScanResultAdapter;
import com.wwl.can.wifi.unit.WifiAdmin;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WiFiActivity extends AppCompatActivity {

    @Bind(R.id.bt_checkwifi) Button btCheckwifi;
    @Bind(R.id.bt_openwifi) Button btOpenwifi;
    @Bind(R.id.bt_closewifi) Button btClosewifi;
    @Bind(R.id.bt_scanwifi) Button btScanwifi;
    @Bind(R.id.bt_getwifi) Button btGetwifi;
    @Bind(R.id.tv_wifimessage) TextView tvWifimessage;
    @Bind(R.id.bt_finish) Button btFinish;
    @Bind(R.id.lv_scanresult) ListView lvScanresult;
    protected WifiAdmin mWifiAdmin;
    private List<ScanResult> mWifiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);
        mWifiAdmin = new WifiAdmin(WiFiActivity.this);
        ButterKnife.bind(this);
        IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(mReceiver, filter);
        iniView();
    }

    private void iniView(){
    }
    private void setData(){
        if (mWifiList !=null && mWifiList.size() > 0){
            lvScanresult.setAdapter(new WifiScanResultAdapter(this,mWifiList));
        }
    }

    @OnClick({R.id.bt_checkwifi, R.id.bt_openwifi, R.id.bt_closewifi, R.id.bt_scanwifi, R.id.bt_getwifi})
    public void onViewClicked(View view) {
        Context context = WiFiActivity.this;
        switch (view.getId()) {
            case R.id.bt_checkwifi:
                mWifiAdmin.checkState(context);
                break;
            case R.id.bt_openwifi:
                mWifiAdmin.openWifi(context);
                break;
            case R.id.bt_closewifi:
                mWifiAdmin.closeWifi(context);
                break;
            case R.id.bt_scanwifi:
                mWifiAdmin.startScan(context);
                mWifiList = mWifiAdmin.getWifiList();
                if (mWifiList != null && mWifiList.size() > 0) {
                    setData();
                }
                break;
            case R.id.bt_getwifi:
                setWifiMessage(WiFiActivity.this);
                break;
        }
    }

    private void setWifiMessage(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String wifiSSID = info.getSSID();
        Toast.makeText(context, wifiSSID + "链接成功", Toast.LENGTH_LONG).show();
        tvWifimessage.setText(info.toString());
    }

    //wifi状态变化广播接受
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiInfo.isConnected()) {
                setWifiMessage(context);
            }
        }
    };

    @OnClick(R.id.bt_finish)
    public void onViewClicked() {
        this.finish();
    }
}

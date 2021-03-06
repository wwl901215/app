package com.wwl.can.wifi.ui;

import android.app.Activity;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wwl.can.R;
import com.wwl.can.learn.wifi.WifiClientActivity;
import com.wwl.can.learn.wifi.WifiServerActivity;
import com.wwl.can.wifi.adapter.WifiScanResultAdapter;
import com.wwl.can.wifi.unit.SomeMethod;
import com.wwl.can.wifi.unit.WifiAdmin;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WiFiActivity extends Activity {

    @Bind(R.id.bt_checkwifi) Button btCheckwifi;
    @Bind(R.id.bt_openwifi) Button btOpenwifi;
    @Bind(R.id.bt_closewifi) Button btClosewifi;
    @Bind(R.id.bt_scanwifi) Button btScanwifi;
    @Bind(R.id.bt_getwifi) Button btGetwifi;
    @Bind(R.id.tv_wifimessage) TextView tvWifimessage;
    @Bind(R.id.bt_finish) Button btFinish;
    @Bind(R.id.lv_scanresult) ListView lvScanresult;
    protected WifiAdmin mWifiAdmin;
    @Bind(R.id.tb_server) Button tbServer;
    @Bind(R.id.tb_client) Button tbClient;
    private List<ScanResult> mWifiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);
        mWifiAdmin = new WifiAdmin(WiFiActivity.this);
        ButterKnife.bind(this);
        IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(mReceiver, filter);
        iniView();
    }

    private void iniView() {
    }

    private void setData() {
        if (mWifiList != null && mWifiList.size() > 0) {
            lvScanresult.setAdapter(new WifiScanResultAdapter(this, mWifiList));
            SomeMethod.setListViewHeightBasedOnChilren(lvScanresult);
            lvScanresult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(WiFiActivity.this, "click item:" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnClick({R.id.bt_checkwifi, R.id.bt_openwifi, R.id.bt_closewifi, R.id.bt_scanwifi, R.id.bt_getwifi,R.id.tb_server, R.id.tb_client})
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
            case R.id.tb_server:
                Intent intent = new Intent(WiFiActivity.this, WifiServerActivity.class);
                startActivity(intent);
                break;
            case R.id.tb_client:
                Intent intent1 = new Intent(WiFiActivity.this, WifiClientActivity.class);
                startActivity(intent1);
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
            final String action = intent.getAction();
            if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                Toast.makeText(context, "扫描到了", Toast.LENGTH_SHORT).show();
            }

        }
    };

    @OnClick(R.id.bt_finish)
    public void onViewClicked() {
        this.finish();
    }

}

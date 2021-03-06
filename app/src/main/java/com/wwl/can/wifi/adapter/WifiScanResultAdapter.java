package com.wwl.can.wifi.adapter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wwl.can.R;

import java.util.List;

/**
 * Created by wangwenliang on 2017/12/1.
 */

public class WifiScanResultAdapter extends BaseAdapter {
    private List<ScanResult> data;
    private Context mcontext;

    public WifiScanResultAdapter(Context context, List<ScanResult> listdata) {
        mcontext = context;
        data = listdata;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WifiViewHolder wifiViewHolder = null;
        if (convertView == null) {
            wifiViewHolder = new WifiViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.wifi_scanresult_listitem, null);
            wifiViewHolder.ssid = (TextView) convertView.findViewById(R.id.ssid);
            wifiViewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(wifiViewHolder);
        } else {
            wifiViewHolder = (WifiViewHolder) convertView.getTag();
        }

        ScanResult scanResult = data.get(position);
        if (scanResult != null){
            wifiViewHolder.ssid.setText(scanResult.SSID);
            if (scanResult.capabilities.contains("WEP") ||
                    scanResult.capabilities.contains("PSK") ||
                    scanResult.capabilities.contains("EAP")){
                wifiViewHolder.iv.setImageResource(R.mipmap.wifilock);
            }else {
                wifiViewHolder.iv.setImageResource(R.mipmap.wifiopen);
            }
        }
        return convertView;
    }

    private static class WifiViewHolder {
        TextView ssid;
        ImageView iv;
    }
}

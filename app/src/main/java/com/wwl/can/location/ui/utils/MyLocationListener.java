package com.wwl.can.location.ui.utils;

import android.util.Log;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

/**
 * @version V1.0
 * @Created by: 艾克斯 .
 * @Date: 2018-08-05
 * @Package: com.wwl.can.location.ui.utils
 * @Description: ${TODO}
 * @author: 王文亮
 * @邮箱： wwl901215@163.com
 */

public class MyLocationListener extends BDAbstractLocationListener{
    private double lat;
    private double longit;
    private GetLL getLLLins;

    public  void setLin(GetLL getLL) {
        getLLLins = getLL;
    }
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
//此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取经纬度相关（常用）的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

        double latitude = bdLocation.getLatitude();    //获取纬度信息
        double longitude = bdLocation.getLongitude();    //获取经度信息
        float radius = bdLocation.getRadius();    //获取定位精度，默认值为0.0f

        String coorType = bdLocation.getCoorType();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

        int errorCode = bdLocation.getLocType();
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

        lat = latitude;
        longit = longitude;
        Log.e("===== lat", String.valueOf(latitude));
        Log.e("===== longi",String.valueOf(longitude));
        if (getLLLins != null) {
            getLLLins.getLL(lat,longit);
        }
    }

    public interface GetLL {
        void getLL(double lat, double lot);
    }
}

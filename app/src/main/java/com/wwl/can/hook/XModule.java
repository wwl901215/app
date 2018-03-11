package com.wwl.can.hook;

import android.telephony.TelephonyManager;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @version V1.0
 * @Created by: 艾克斯 .
 * @Date: 2018-03-11
 * @Package: com.wwl.can.hook
 * @Description: ${TODO}
 * @author: 王文亮
 * @邮箱： wwl901215@163.com
 */

public class XModule implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if(loadPackageParam.packageName.equals("com.example.test")){
            XposedBridge.log("XLZH " + loadPackageParam.packageName);
            XposedHelpers.findAndHookMethod(TelephonyManager.class, "getDeviceId", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    return "this is imei";
                }
            });
            XposedHelpers.findAndHookMethod(TelephonyManager.class, "getSubscriberId", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    return "this is imsi";
                }
            });
        }
    }
}

package com.wwl.can.webview;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangwenliang on 2018/1/19.
 */

public class JsGetAndroidFun extends Object {
    Context context;
    public JsGetAndroidFun(Context context){
        this.context = context;
    }
    @JavascriptInterface
    public void sayHello(String msg){
        Log.e("jsresult","来自js的问候："+msg);
        Toast.makeText(context,"来自js的问候："+msg,Toast.LENGTH_SHORT).show();
//        try {
//            method.invoke("aaa");
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }
}

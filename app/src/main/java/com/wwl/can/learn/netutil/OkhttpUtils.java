package com.wwl.can.learn.netutil;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    private static OkhttpUtils instance;
    private OkHttpClient client;

    private OkhttpUtils() {
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    //初始化实例对象
    public static void initInstanse() {
        if (instance == null) {
            synchronized (OkhttpUtils.class) {
                if (instance == null) {
                    instance = new OkhttpUtils();
                }
            }
        }
    }

    public static OkhttpUtils getInstance() {
        return instance;
    }

    public OkhttpUtils get(String url, HashMap<String, String> map) {
        String newUrl = url;
        if (map != null && map.get("beforeCode") != null) {
            if (newUrl.endsWith("/")) {
                newUrl = url + map.get("beforeCode");
            } else {
                newUrl = url + "/" + map.get("beforeCode");
            }
        }
        Request.Builder builder = new Request.Builder();
        builder.url(newUrl);
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request request = builder.build();

        Call call = client.newCall(request);
        try {
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(string);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("aaaaaaaaaa",jsonObject.toString());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

}

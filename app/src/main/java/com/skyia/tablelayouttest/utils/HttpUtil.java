package com.skyia.tablelayouttest.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Skyia_ccf on 2018/1/8.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(final String address, final Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}

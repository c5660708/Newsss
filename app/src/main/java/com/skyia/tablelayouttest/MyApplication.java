package com.skyia.tablelayouttest;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Skyia_ccf on 2018/2/5.
 */

public class MyApplication extends Application {
    private static Application sApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getApplication() {
        return sApplication;
    }

    public static List<String> getAllNewsType() {
        String [] s = {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
        return Arrays.asList(s);
    }
}

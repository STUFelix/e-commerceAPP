package com.evan.fastec;

import android.app.Application;

import com.evan.latte.app.Latte;


/**
 * Author zch@geniatech.com
 * Time  2018/9/13 20:05
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withHost("https://github.com/shanjiaxiang/FastEC/blob/master/example/src/main/java/com/shanjiaxiang/fastec/example/ExampleApp.java/").configure();
    }
}

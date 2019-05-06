package com.evan.latte.ui;

import android.content.Context;

import com.evan.latte.utils.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @author Evan_zch
 * Program: FastEC
 * Time  2018-09-17 22:32
 */
public class LoaderCreator {
    private static final String TAG = "LoaderCreator";
    private static final WeakHashMap<String, Indicator> INDICATOR_MAP = new WeakHashMap<>();


   public static AVLoadingIndicatorView create(Context context, String type) {
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);

        if (INDICATOR_MAP.get(type) == null) {
            Indicator indicator = getIndicator(type);
            INDICATOR_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(INDICATOR_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {

       if (name == null || name.isEmpty()){
           return null;
       }
        StringBuilder stringBuilder = new StringBuilder();
        String defaultPageName = AVLoadingIndicatorView.class.getPackage().getName();

        if (!name.contains(".")) {
            stringBuilder.append(defaultPageName)
                    .append(".indicators")
                    .append(".");
        }

       stringBuilder.append(name);

        try {
            Class<?> drawableClass = Class.forName(stringBuilder.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            LogUtil.e(TAG+"--getIndicator  error="+e.getMessage());
            e.printStackTrace();
            return null;
        }


    }
}

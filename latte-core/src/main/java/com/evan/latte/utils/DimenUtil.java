package com.evan.latte.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.evan.latte.app.Latte;

/**
 * @author Evan_zch
 * Program: FastEC
 * Time  2018-09-20 19:49
 */
public class DimenUtil {

    public static int getScreenWidth(){
        Resources resources =
                Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics =
                resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(){
        Resources resources =
                Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics =
                resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}

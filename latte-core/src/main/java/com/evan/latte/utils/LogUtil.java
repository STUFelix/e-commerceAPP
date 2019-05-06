package com.evan.latte.utils;

import android.util.Log;

/**
 * Program: FastEC
 *
 * @author Evan_zch
 * Time  2018-09-16 09:22
 */
public class LogUtil {

    private static final String TAG = "LATTE";
    private static final boolean DEBUG = true;


    public static void d(String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }

    public static void e(String message) {
        if (DEBUG) {
            Log.e(TAG, message);
        }
    }

    public static void i(String message) {
        if (DEBUG) {
            Log.i(TAG, message);
        }
    }
}

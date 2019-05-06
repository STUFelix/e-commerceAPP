package com.evan.latte.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.evan.latte.R;
import com.evan.latte.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * @author Evan_zch
 * Program: FastEC
 * Time  2018-09-20 19:44
 */
public class LatterLoader {


    // 默认loading Type
    private static final String LOADING_DEFAULT_TYPE = LoaderType.BallClipRotateIndicator.name();
    // 默认变化比例
    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    public static void showLoading(Context context, String type) {
       final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
      final   AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(context, type);
        dialog.setContentView(avLoadingIndicatorView);

        int screenWidth = DimenUtil.getScreenWidth();
        int screenHeight = DimenUtil.getScreenHeight();
        final   Window dialogWindow = dialog.getWindow();

        // 注意这里是当dialogWindow不为空，自己敲的时候给自己埋雷了。。。
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = screenWidth / LOADER_SIZE_SCALE;
            lp.height = screenHeight / LOADER_SIZE_SCALE;
//            lp.height = lp.height + screenHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;

        }
        LOADERS.add(dialog);
        dialog.show();
    }




    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS) {
            if (dialog!= null){
                dialog.cancel();
            }
        }
    }

    public static void showLoading(Context context){
        showLoading(context,LOADING_DEFAULT_TYPE);
    }
}

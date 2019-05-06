package com.evan.latte.net;

import android.content.Context;
import android.os.Handler;

import com.evan.latte.net.callback.IError;
import com.evan.latte.net.callback.IFail;
import com.evan.latte.net.callback.IRequest;
import com.evan.latte.net.callback.ISuccess;
import com.evan.latte.ui.LatterLoader;
import com.evan.latte.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Evan_zch
 * Program: FastEC
 * Time  2018-09-17 21:13
 */
public class RequestCallbacks implements Callback<String> {

    private static final String TAG = "RequestCallbacks";
    private final ISuccess ISUCCESS;
    private final IFail IFAIL;
    private final IError IERROR;
    private final IRequest IREQUEST;
    private final Context CONTEXT;
    private final String LOADER_TYPE;

    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(ISuccess success, IFail fail, IError error, IRequest request, Context context, String loaderType) {

        this.ISUCCESS = success;
        this.IFAIL = fail;
        this.IERROR = error;
        this.IREQUEST = request;
        this.CONTEXT = context;
        this.LOADER_TYPE = loaderType;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (ISUCCESS != null) {
                    LogUtil.d(TAG + "--onResponse  success");
                    ISUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (IERROR != null) {
                IERROR.onError(response.message());
            }
        }
        dismissLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if (IFAIL != null) {
            IFAIL.onFailed(t.getMessage());
        }

        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }

        dismissLoading();
    }

    public void dismissLoading() {
        if (LOADER_TYPE != null) {

            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatterLoader.stopLoading();
                }
            }, 3000);

        }
    }


}

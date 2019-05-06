package com.evan.fastec;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.evan.latte.delegates.LatteDelegate;
import com.evan.latte.net.RestClient;
import com.evan.latte.net.callback.IError;
import com.evan.latte.net.callback.IFail;
import com.evan.latte.net.callback.IRequest;
import com.evan.latte.net.callback.ISuccess;
import com.evan.latte.ui.LoaderType;
import com.evan.latte.utils.LogUtil;

/**
 * Program: FastEC
 *
 * @author Evan_zch
 * Time  2018-09-14 22:04
 */
public class ExampleDelegate extends LatteDelegate {

    private static final String TAG = "ExampleDelegate";

    @Override
    protected Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://www.jianshu.com/p/5f5dea7b5ea1/")
                .loader(getActivity(), LoaderType.BallGridPulseIndicator.name())
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        LogUtil.d(TAG + "--testRestClient  onRequestStart");
                    }

                    @Override
                    public void onRequestEnd() {
                        LogUtil.d(TAG + "--testRestClient  onRequestEnd");
                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                }).fail(new IFail() {
            @Override
            public void onFailed(String error) {

            }
        }).error(new IError() {
            @Override
            public void onError(String error) {

            }
        }).builder().get();
    }
}

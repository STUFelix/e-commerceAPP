package com.evan.latte.net;

import android.content.Context;

import com.evan.latte.net.callback.IError;
import com.evan.latte.net.callback.IFail;
import com.evan.latte.net.callback.IRequest;
import com.evan.latte.net.callback.ISuccess;
import com.evan.latte.ui.LatterLoader;
import com.evan.latte.utils.LogUtil;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author Evan_zch
 * Program: FastEC
 * Time  2018-09-16 10:32
 */
public class RestClient {

    private final String URL;
    private final WeakHashMap<String, Object> mPARAMS = RestCreator.getParams();
    private final ISuccess ISUCCESS;
    private final IFail IFAIL;
    private final IError IERROR;
    private final IRequest IREQUEST;
    private final Context CONTEXT;
    private final String LOADER_TYPE;

    private final RequestBody REQUESTBODY;

    public RestClient(String mUrl, WeakHashMap<String, Object> mParams, ISuccess mISuccess,
                      IFail mIFail, IError mIError, IRequest mIRequest, RequestBody requestBody, Context context,String loaderType) {
        this.URL = mUrl;
        this.mPARAMS.putAll(mParams);
        this.ISUCCESS = mISuccess;
        this.IFAIL = mIFail;
        this.IERROR = mIError;
        this.IREQUEST = mIRequest;
        this.REQUESTBODY = requestBody;
        this.CONTEXT = context;
        this.LOADER_TYPE = loaderType;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private static final String TAG = "RestClient";
    public void request(HttpMethod httpMethod) {
        RestService restService = RestCreator.getRestService();
        LogUtil.e(TAG+"--request  restService is null?="+(restService == null));

        if (LOADER_TYPE != null){
            LatterLoader.showLoading(CONTEXT, LOADER_TYPE);
        }

        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }


        Call<String> mCall = null;
        switch (httpMethod) {
            case GET:
                mCall = restService.get(URL, mPARAMS);
                break;
            case PUT:
                mCall = restService.put(URL, mPARAMS);
                break;
            case POST:
                mCall = restService.post(URL, mPARAMS);
                break;
            case DELETE:
                mCall = restService.delete(URL, mPARAMS);
                break;
            default:
                break;
        }

        if (mCall != null) {
            mCall.enqueue(getRequestCallback());
        }

    }


    public Callback<String> getRequestCallback() {
        return new RequestCallbacks(ISUCCESS, IFAIL, IERROR, IREQUEST,CONTEXT,LOADER_TYPE);
    }

    public void get(){
        request(HttpMethod.GET);
    }

    public void post(){
        request(HttpMethod.POST);
    }

    public void put(){
        request(HttpMethod.PUT);
    }

    public void delete(){
        request(HttpMethod.DELETE);
    }
}

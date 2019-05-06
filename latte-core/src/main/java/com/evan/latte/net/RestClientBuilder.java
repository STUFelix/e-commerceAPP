package com.evan.latte.net;

import android.content.Context;

import com.evan.latte.net.callback.IError;
import com.evan.latte.net.callback.IFail;
import com.evan.latte.net.callback.IRequest;
import com.evan.latte.net.callback.ISuccess;
import com.evan.latte.ui.LoaderType;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Evan_zch
 * Program: FastEC
 * Time  2018-09-16 17:19
 */
public class RestClientBuilder {

    private String mUrl;
    private WeakHashMap<String, Object> mParams = RestCreator.getParams();
    private ISuccess mISuccess;
    private IFail mIFail;
    private IError mIError;
    private IRequest mIRequest;
    private RequestBody mRequestBody;

    private Context mContext;
    private String mLoaderType;


    public RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public RestClientBuilder fail(IFail iFail) {
        this.mIFail = iFail;
        return this;
    }

    public RestClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public RestClientBuilder params(String key, Object params) {
        this.mParams.put(key, params);
        return this;


    }

    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.mParams.putAll(params);
        return this;
    }

    public RestClientBuilder raw(String raw) {
        this.mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }
    public RestClientBuilder loader(Context context,String type) {
        this.mContext = context;
        this.mLoaderType = type;
        return this;
    }

    public RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderType = LoaderType.BallSpinFadeLoaderIndicator.name();
        return this;
    }


    public RestClient builder() {
        return new RestClient(mUrl, mParams, mISuccess, mIFail, mIError, mIRequest, mRequestBody,mContext,mLoaderType);
    }


}



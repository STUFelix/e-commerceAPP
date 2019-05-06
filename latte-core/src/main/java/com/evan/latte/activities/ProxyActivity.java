package com.evan.latte.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.evan.latte.R;
import com.evan.latte.delegates.LatteDelegate;
import com.evan.latte.utils.LogUtil;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Program: FastEC
 * Author Evan_zch
 * Time  2018-09-13 22:12
 */
public abstract class ProxyActivity extends SupportActivity {
    private static final String TAG = "ProxyActivity";
    public abstract LatteDelegate setLatteDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG+"--onCreate");
        super.onCreate(savedInstanceState);
        initDelegate(savedInstanceState);
    }


    public void initDelegate(@Nullable Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setLatteDelegate());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}

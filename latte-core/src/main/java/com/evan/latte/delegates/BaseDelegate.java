package com.evan.latte.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evan.latte.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Program: FastEC
 * Author Evan_zch
 * Time  2018-09-13 22:11
 */
public abstract class BaseDelegate extends SwipeBackFragment {
    private static final String TAG = "BaseDelegate";

    protected abstract Object setLayout();

    protected abstract void onBindView(@NonNull Bundle savedInstanceState, View rootView);

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG+"--BaseDelegate  onCreateView");
        View rootView = null;
        if (setLayout() instanceof Integer) {
           rootView =  inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }

        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            assert savedInstanceState != null;
            onBindView(savedInstanceState, rootView);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}

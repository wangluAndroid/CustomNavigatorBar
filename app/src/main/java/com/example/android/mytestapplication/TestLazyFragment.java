package com.example.android.mytestapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.mytestapplication.lazyfragment.LazyFragment;

/**
 * Created by WangLu on 2016/11/29.
 * E-mail:wang_lu90125@163.com
 */

public class TestLazyFragment extends LazyFragment {

    private boolean isPrepare ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepare = true ;

        lazyLoad();//加载数据
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Nullable
    @Override
    protected int getLayoutID() {
        return 0;
    }

    @Override
    protected void iniView(View view) {

    }


    @Override
    protected void initializeSuccessButInvisible() {

    }

    @Override
    protected void lazyLoad() {
//        //加载数据的条件：1.初始化完毕 ； 2.当前的Fragment对用户可见
//        if (isPrepare && isVisible) {
//
//        }else{
//            //否则 什么都不加载
//            return ;
//        }
    }
}

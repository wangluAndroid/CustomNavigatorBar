package com.example.android.mytestapplication;

import android.view.View;
import android.widget.TextView;

import com.example.android.mytestapplication.lazyfragment.LazyFragment;

/**
 * Created by WangLu on 2016/11/29.
 * E-mail:wang_lu90125@163.com
 */

public class TwoFragment extends LazyFragment {

    private TextView textView;
    String mTitle;

    /*  public TwoFragment() {

          mTitle = getArguments().getString("title");
      }*/
    @Override
    protected int getLayoutID() {
        return R.layout.fragment;
    }

    @Override
    protected void iniView(View view) {
        mTitle = getArguments().getString("title");
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(mTitle);
    }

    @Override
    protected void initializeSuccessButInvisible() {
        System.out.println("-----TwoFragment---------initializeSuccessButInvisible-------------");
    }

    @Override
    protected void lazyLoad() {
        System.out.println("-----TwoFragment---------lazyLoad-------------");
    }
    @Override
    protected void refreshDataSources() {
        super.refreshDataSources();
        System.out.println("-----TwoFragment---------refreshDataSources-------------");
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        System.out.println("---TwoFragment--------setUserVisibleHint-----------------"+isVisibleToUser);
    }
}

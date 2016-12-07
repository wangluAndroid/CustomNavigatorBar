package com.example.android.mytestapplication;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mytestapplication.customvView.CustomNavigatorBar;
import com.example.android.mytestapplication.lazyfragment.LazyFragment;

/**
 * Created by WangLu on 2016/11/29.
 * E-mail:wang_lu90125@163.com
 */

public class OneFragment extends LazyFragment {

    private TextView textView;
    String mTitle ;

   /* public OneFragment() {
        mTitle = getArguments().getString("title");
    }*/

    @Override
    protected int getLayoutID() {
        return R.layout.test;
    }

    @Override
    protected void iniView(View view) {
        mTitle = getArguments().getString("title");

        CustomNavigatorBar customNavigatorBar = (CustomNavigatorBar) getView(R.id.customView);


        /**
         * 第一种监听的具体实现
         */
        customNavigatorBar.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"left",Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 第二种监听的具体实现
         */
        customNavigatorBar.addViewClickListener(new CustomNavigatorBar.OnCustomClickListener() {
            @Override
            public void onClickListener(View rootView) {
                switch (rootView.getId()) {
                    case R.id.right_image:
                        Toast.makeText(getActivity(),"right_image is clicked",Toast.LENGTH_SHORT).show();
                        break ;
                    case R.id.left_image:
                        Toast.makeText(getActivity(),"left_image is clicked",Toast.LENGTH_SHORT).show();
                        break ;
                }
            }
        });

    }

    @Override
    protected void initializeSuccessButInvisible() {
        System.out.println("-----OneFragment---------initializeSuccessButInvisible-------------");
    }

    @Override
    protected void lazyLoad() {
        System.out.println("-----OneFragment---------lazyLoad-------------");
    }

    @Override
    protected void refreshDataSources() {
        super.refreshDataSources();
        System.out.println("-----OneFragment---------refreshDataSources-------------");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        System.out.println("---OneFragment--------setUserVisibleHint-----------------"+isVisibleToUser);
    }
}

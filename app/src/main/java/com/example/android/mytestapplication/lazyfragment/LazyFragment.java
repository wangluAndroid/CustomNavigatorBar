package com.example.android.mytestapplication.lazyfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by WangLu on 2016/12/5.
 * E-mail:wang_lu90125@163.com
 */

public abstract class LazyFragment extends Fragment {
    //判断当前Fragment是否成功请求回数据，用来控制当前fragment可见时是否刷新数据
    protected boolean isLoadDataSuccess ;
    //当前的Fragment是否初始化完成
    protected boolean isInitialized ;

    private View view ;



    /**
     * 此方法在onCreateView()方法加载前执行，用来进行其他小量的初始化操作（比如初始化需要通过AIDL调用的远程服务）
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //在此方法中做fragment是否初始化完毕判断，防止出现NullPointException
        initDatas();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutID(), container, false);
        isInitialized = true ;
        iniView(view);
        initDatas();
        return view;
    }


    private void initDatas() {

        //如果初始化未完成，直接return
        if(!isInitialized){
            return ;
        }

        //初始化完成 但不可见
        if (!getUserVisibleHint()) {
            //预加载的和将要不可见的 会走此方法
            //每次切换都会走此方法
            initializeSuccessButInvisible();
        }else{//初始化完成 并可见
            //lazyLoad 数据
            if (isLoadDataSuccess) {//上次加载数据成功，以后做一些处理的逻辑（比如刷新数据）
                refreshDataSources();
            }else{
                //加载数据
                lazyLoad();
                isLoadDataSuccess = true ;
            }

        }
    }


    /**
     * 优化抽取findViewById
     * @param resourcesId
     * @param <T>
     * @return
     */
    protected <T extends View> T getView(@Nullable int resourcesId){
        return (T) view.findViewById(resourcesId);
    }

    /**
     * reset all state
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInitialized = false ;
        isLoadDataSuccess = false ;
    }

    /**
     * 刷新数据的方法
     * 空实现
     * 如果需要刷新数据 需要重写
     */
    protected void refreshDataSources() {
    }

    protected abstract int getLayoutID();

    protected abstract void iniView(View view);

    protected abstract void initializeSuccessButInvisible();

    protected abstract  void lazyLoad();
}

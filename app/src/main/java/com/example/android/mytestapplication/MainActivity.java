package com.example.android.mytestapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mytestapplication.customvView.CustomNavigatorBar;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPager upViewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

//        initRecyclerView();


        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        final WebView webView = (WebView) findViewById(R.id.webView);
//        webView.loadUrl("http://www.baidu.com");
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
////                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
////                swipeRefreshLayout.setRefreshing(true);
//            }
//
//        });
//
//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
//        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);
//
//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                webView.reload();
//            }
//        });


    }

//    private void initRecyclerView() {recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        recyclerView.setAdapter(adapter);
//
//        List<String> list_adapter = new ArrayList<>();
//        for (int i = 0 ;i<20 ;i++) {
//            list_adapter.add("item----->" + i);
//        }
//        adapter.addList(list_adapter);
//        adapter.notifyDataSetChanged();
//    }
//
//    class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{
//        List<String> list_adapter = null ;
//        LayoutInflater inflater ;
//
//        public MyRecyclerAdapter(Context context ) {
//            inflater = LayoutInflater.from(context);
//
//        }
//
//        public void addList(List<String> list) {
//            list_adapter = list ;
//        }
//
//        @Override
//        public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            MyViewHolder viewHolder = new MyViewHolder(inflater.inflate(R.layout.item,parent,false));
//            return viewHolder ;
//        }
//
//        @Override
//        public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, int position) {
//            holder.textView.setText(list_adapter.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return list_adapter.size();
//        }
//
//         class MyViewHolder extends RecyclerView.ViewHolder{
//
//             TextView textView ;
//
//             public MyViewHolder(View itemView) {
//                 super(itemView);
//                 textView = (TextView) itemView.findViewById(R.id.textView);
//             }
//
//         }
//
//
//    }


    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private List<Fragment> list;
    private List<String> title;

    public void setUpViewPager(ViewPager viewPager) {
        list = new ArrayList<>();
        title = new ArrayList<>();
//        for (int i = 0 ;i<4;i++) {
//            OneFragment fragment = new OneFragment();
//            Bundle data = new Bundle();
//            data.putInt("id", 0);
//            data.putString("title", "title:"+i);
//            fragment.setArguments(data);
//
//            list.add(fragment);
//            title.add("title:"+i);
//        }


        OneFragment oneFragment = new OneFragment();
        Bundle data1 = new Bundle();
        data1.putInt("id", 0);
        data1.putString("title", "oneFragment");
        oneFragment.setArguments(data1);
        list.add(oneFragment);
        title.add("推荐");

        TwoFragment twoFragment = new TwoFragment();
        Bundle data2 = new Bundle();
        data2.putInt("id", 1);
        data2.putString("title", "twoFragment");
        twoFragment.setArguments(data2);
        list.add(twoFragment);
        title.add("视频");

        ThreeFragment threeFragment = new ThreeFragment();
        Bundle data3 = new Bundle();
        data3.putInt("id", 2);
        data3.putString("title", "threeFragment");
        threeFragment.setArguments(data3);
        list.add(threeFragment);
        title.add("体育");

        FourFragment fourFragment = new FourFragment();
        Bundle data4 = new Bundle();
        data4.putInt("id", 3);
        data4.putString("title", "fourFragment");
        fourFragment.setArguments(data4);
        list.add(fourFragment);
        title.add("关注");


        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
    }
}

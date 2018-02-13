package com.skyia.tablelayouttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.skyia.tablelayouttest.bean.NewsBean;
import com.skyia.tablelayouttest.utils.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Skyia_ccf on 2018/1/2.
 */

public class NewsListFragment extends Fragment implements NewsItemClickCallback{
    private String url;
    private String type;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsListRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<NewsBean.ResultBean.DataBean> mDataBeans;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, null);
        initView(view);
        type = getArguments().getString("type");
        url = "http://v.juhe.cn/toutiao/index?type="+type+"&key=01af65745ece9874aa369e1ed584c4aa";
        requestNews();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestNews();
                Toast.makeText(getActivity(),"刷新成功",Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    public void requestNews(){
        final Gson gson = new Gson();
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String responseText = response.body().string();
                    NewsBean newsBean = gson.fromJson(responseText,NewsBean.class);
                    mDataBeans = newsBean.getResult().getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showNews(mDataBeans,getActivity());
                        }
                    });
                }catch (JsonSyntaxException e){

                }
            }
        });
    }

    private void initView(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_list_recyclerview);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_swipe_refresh);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewsListRecyclerViewAdapter(null,getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void showNews(List<NewsBean.ResultBean.DataBean> list,Context context){
        mAdapter = new NewsListRecyclerViewAdapter(list,context);
        mAdapter.setNewsClickListener(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onNewsItemClick(NewsBean.ResultBean.DataBean dataBean) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
//        String[] urls = new String[3];
//        //获取新闻详情url
//        urls[0] = dataBean.getUrl();
//        //获取新闻图片url
//        urls[1] = dataBean.getThumbnail_pic_s02();
//        //获取新闻title
//        urls[2] = dataBean.getTitle();
        String urls = dataBean.getUrl();
        intent.putExtra("urls", urls);
        getActivity().startActivity(intent);
//        Toast.makeText(getActivity(),"click",Toast.LENGTH_SHORT).show();
    }

    //创建每一类新闻fragment的时候为其赋值新闻类型参数（tabId）
    public static NewsListFragment newInstance(int newsType) {
        Bundle args = new Bundle();
        args.putInt("newsType", newsType);
        NewsListFragment newsListFra = new NewsListFragment();
        newsListFra.setArguments(args);
        return newsListFra;
    }
}

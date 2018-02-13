package com.skyia.tablelayouttest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Skyia_ccf on 2017/12/28.
 */

public class NewsTabAndPagerFragment extends Fragment {
    private TabLayout mTablayout;
    private ImageView mAddButton;
    private ViewPager mViewPager;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        sp = this.getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mAddButton = (ImageView) view.findViewById(R.id.add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddTabsActivity.class);
                startActivity(intent);
            }
        });
        mViewPager.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        NewsListFragment fg1 = new NewsListFragment();
        Bundle args1 = new Bundle();
        args1.putString("type","top");
        fg1.setArguments(args1);

        NewsListFragment fg2 = new NewsListFragment();
        Bundle args2 = new Bundle();
        args2.putString("type", "shehui");
        fg2.setArguments(args2);

        NewsListFragment fg3 = new NewsListFragment();
        Bundle args3 = new Bundle();
        args3.putString("type", "guonei");
        fg3.setArguments(args3);

        NewsListFragment fg4 = new NewsListFragment();
        Bundle args4 = new Bundle();
        args4.putString("type", "guoji");
        fg4.setArguments(args4);

        NewsListFragment fg5 = new NewsListFragment();
        Bundle args5 = new Bundle();
        args5.putString("type", "yule");
        fg5.setArguments(args5);

        NewsListFragment fg6 = new NewsListFragment();
        Bundle args6 = new Bundle();
        args6.putString("type", "tiyu");
        fg6.setArguments(args6);

        NewsListFragment fg7 = new NewsListFragment();
        Bundle args7 = new Bundle();
        args7.putString("text", "junshi");
        fg7.setArguments(args7);

        NewsListFragment fg8 = new NewsListFragment();
        Bundle args8 = new Bundle();
        args8.putString("type", "keji");
        fg8.setArguments(args8);

        NewsListFragment fg9 = new NewsListFragment();
        Bundle args9 = new Bundle();
        args9.putString("type", "caijing");
        fg9.setArguments(args9);

        NewsListFragment fg10 = new NewsListFragment();
        Bundle args10 = new Bundle();
        args10.putString("type","shishang");
        fg10.setArguments(args10);

        adapter.addFragment(fg1, "头条");
        adapter.addFragment(fg2, "社会");
        adapter.addFragment(fg3, "国内");
        adapter.addFragment(fg4, "国际");
        adapter.addFragment(fg5, "娱乐");
        adapter.addFragment(fg6, "体育");
        adapter.addFragment(fg7, "军事");
        adapter.addFragment(fg8, "科技");
        adapter.addFragment(fg9, "财经");
//        adapter.addFragment(fg10,"时尚");
        if (sp.getBoolean("shishang",true) == true){
            adapter.addFragment(fg10,"时尚");
        }else {
            adapter.removeFragment(fg10,"时尚");
        }
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewPager);
        return view;
    }
}

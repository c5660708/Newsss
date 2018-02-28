package com.skyia.tablelayouttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

/**
 * Created by Skyia_ccf on 2018/2/23.
 */

public class AddTabs extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<Boolean> mCheckedTypes;
    private String[] mAllNewsTypeTitle;
    private boolean mChannelChanges = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvNewsType);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAllNewsTypeTitle = getResources().getStringArray(R.array.allNewsTypes);
        mCheckedTypes = new ArrayList<>();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("newsType");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadNewsChannel();
        mRecyclerView.setAdapter(new NewsTypeAddRecyclerViewAdapter(mAllNewsTypeTitle,mCheckedTypes));
        setOnNewsTypeChangeListener(mRecyclerView);
    }

    private void setOnNewsTypeChangeListener(RecyclerView recyclerView) {
        NewsTypeAddRecyclerViewAdapter adapter = (NewsTypeAddRecyclerViewAdapter) recyclerView.getAdapter();
    }

    private void loadNewsChannel() {
        for (String title : mAllNewsTypeTitle){
            mCheckedTypes.add(MyApplication.getApplication().getSharedPreferences("NewsChannelPrefer",MODE_PRIVATE).getBoolean(title,false));
        }
    }


}

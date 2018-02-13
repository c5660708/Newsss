package com.skyia.tablelayouttest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyia.tablelayouttest.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skyia_ccf on 2018/1/8.
 */

public class NewsListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int LEFT_IMAGE_HOLDER = 1;
    public static final int THREE_IMAGE_HOLDER = 2;
    public static final int PULL_IMAGE_HOLDER = 3;

    private List<NewsBean.ResultBean.DataBean> mDataBeans;
    private Context mContext;
    private NewsItemClickCallback mNewsClickListener;

    public void setmDataBeans(List<NewsBean.ResultBean.DataBean> dataBeans){
        this.mDataBeans = dataBeans;
        notifyDataSetChanged();
    }


    public void setNewsClickListener(NewsItemClickCallback newsClickListener){
        this.mNewsClickListener = newsClickListener;
    }

    public NewsListRecyclerViewAdapter(List<NewsBean.ResultBean.DataBean> dataBeanList, Context context){
        this.mDataBeans = dataBeanList;
        this.mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case THREE_IMAGE_HOLDER:
                view = LayoutInflater.from(mContext).inflate(R.layout.news_list_item_three,parent,false);
                holder = new ThreeImageHolder(view,mDataBeans);
                break;
            case LEFT_IMAGE_HOLDER:
                view = LayoutInflater.from(mContext).inflate(R.layout.news_list_item_left,parent,false);
                holder = new LeftImageHolder(view,mDataBeans);
                break;
            case PULL_IMAGE_HOLDER:
                view = LayoutInflater.from(mContext).inflate(R.layout.news_list_item_pull,parent,false);
                holder = new PullImageHolder(view,mDataBeans);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case THREE_IMAGE_HOLDER:
                final ThreeImageHolder threeImageHolder = (ThreeImageHolder) holder;
                threeImageHolder.mNewsTitleThree.setText(mDataBeans.get(position).getTitle());
                threeImageHolder.mNewsAuthorThree.setText(mDataBeans.get(position).getAuthor_name());
                threeImageHolder.mNewsTimeThree.setText(mDataBeans.get(position).getDate());
                Glide.with(mContext).load(mDataBeans.get(position).getThumbnail_pic_s()).into(threeImageHolder.mImageView1);
                Glide.with(mContext).load(mDataBeans.get(position).getThumbnail_pic_s02()).into(threeImageHolder.mImageView2);
                Glide.with(mContext).load(mDataBeans.get(position).getThumbnail_pic_s03()).into(threeImageHolder.mImageView3);
                break;
            case LEFT_IMAGE_HOLDER:
                final LeftImageHolder leftImageHolder = (LeftImageHolder) holder;
                leftImageHolder.mNewsTitleLeft.setText(mDataBeans.get(position).getTitle());
                leftImageHolder.mNewsAuthorLeft.setText(mDataBeans.get(position).getAuthor_name());
                leftImageHolder.mNewsTimeLeft.setText(mDataBeans.get(position).getDate());
                Glide.with(mContext).load(mDataBeans.get(position).getThumbnail_pic_s()).into(leftImageHolder.mImageLeft);
                break;
            case PULL_IMAGE_HOLDER:
                final PullImageHolder pullImageHolder = (PullImageHolder) holder;
                pullImageHolder.mNewsTitlePull.setText(mDataBeans.get(position).getTitle());
                pullImageHolder.mNewsAtuhorPull.setText(mDataBeans.get(position).getAuthor_name());
                pullImageHolder.mNewsTimePull.setText(mDataBeans.get(position).getDate());
                Glide.with(mContext).load(mDataBeans.get(position).getThumbnail_pic_s()).into(pullImageHolder.mImagePull);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mDataBeans.get(position).getThumbnail_pic_s03()!=null){
            return THREE_IMAGE_HOLDER;
        }else if (mDataBeans.get(position).getThumbnail_pic_s02()!=null){
            return LEFT_IMAGE_HOLDER;
        }else if (mDataBeans.get(position).getThumbnail_pic_s()!=null){
            return PULL_IMAGE_HOLDER;
        }else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        if (mDataBeans == null) {
            return 0;
        }
        return mDataBeans.size();
    }

    public class LeftImageHolder extends RecyclerView.ViewHolder{
        private ImageView mImageLeft;
        private TextView mNewsTitleLeft;
        private TextView mNewsAuthorLeft;
        private TextView mNewsTimeLeft;
        public LeftImageHolder(View itemView, final List<NewsBean.ResultBean.DataBean> dataBean) {
            super(itemView);
            mImageLeft = (ImageView)itemView.findViewById(R.id.image_left);
            mNewsTitleLeft = (TextView)itemView.findViewById(R.id.news_title_left);
            mNewsAuthorLeft = (TextView)itemView.findViewById(R.id.author_left);
            mNewsTimeLeft = (TextView)itemView.findViewById(R.id.time_left);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNewsClickListener.onNewsItemClick(dataBean.get(LeftImageHolder.this.getAdapterPosition()));
                }
            });
        }
    }

    public class ThreeImageHolder extends RecyclerView.ViewHolder{
        private TextView mNewsTitleThree;
        private ImageView mImageView1,mImageView2,mImageView3;
        private TextView mNewsAuthorThree;
        private TextView mNewsTimeThree;
        public ThreeImageHolder(View itemView,final List<NewsBean.ResultBean.DataBean> dataBean) {
            super(itemView);
            mNewsTitleThree = (TextView) itemView.findViewById(R.id.news_title_three);
            mImageView1 = (ImageView) itemView.findViewById(R.id.image1);
            mImageView2 = (ImageView) itemView.findViewById(R.id.image2);
            mImageView3 = (ImageView) itemView.findViewById(R.id.image3);
            mNewsAuthorThree = (TextView)itemView.findViewById(R.id.author_three);
            mNewsTimeThree = (TextView)itemView.findViewById(R.id.time_three);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNewsClickListener.onNewsItemClick(dataBean.get(ThreeImageHolder.this.getAdapterPosition()));
                }
            });
        }
    }

    public class PullImageHolder extends RecyclerView.ViewHolder{
        private TextView mNewsTitlePull;
        private ImageView mImagePull;
        private TextView mNewsAtuhorPull;
        private TextView mNewsTimePull;
        public PullImageHolder(View itemView,final List<NewsBean.ResultBean.DataBean> dataBean) {
            super(itemView);
            mNewsTitlePull = (TextView) itemView.findViewById(R.id.news_title_pull);
            mImagePull = (ImageView) itemView.findViewById(R.id.image_pull);
            mNewsAtuhorPull = (TextView)itemView.findViewById(R.id.author_pull);
            mNewsTimePull = (TextView)itemView.findViewById(R.id.time_pull);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNewsClickListener.onNewsItemClick(dataBean.get(PullImageHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}

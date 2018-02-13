package com.skyia.tablelayouttest;

import com.skyia.tablelayouttest.bean.NewsBean;

/**
 * Created by Skyia_ccf on 2018/1/22.
 */

public interface NewsItemClickCallback {
    void onNewsItemClick(NewsBean.ResultBean.DataBean dataBean);
}

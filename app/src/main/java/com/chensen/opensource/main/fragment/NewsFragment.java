package com.chensen.opensource.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chensen.opensource.R;
import com.chensen.opensource.common.base.BaseTitleFragment;
import com.chensen.opensource.interf.OnTabReselectedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：chensen on 2016/12/13 15:55
 * desc：
 */

public class NewsFragment extends BaseTitleFragment implements OnTabReselectedListener {

    @BindView(R.id.tablayout)
    TabLayout mLayoutTab;
    @BindView(R.id.iv_subscribe)
    ImageView ivSubscribe;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_news;
    }

    @Override
    public void onTabReselected() {

    }



    @OnClick(R.id.iv_subscribe)
    public void onClick() {
    }
}

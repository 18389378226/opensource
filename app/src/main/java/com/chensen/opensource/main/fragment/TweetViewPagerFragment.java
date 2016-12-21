package com.chensen.opensource.main.fragment;

import com.chensen.opensource.R;
import com.chensen.opensource.common.base.BaseFragment;
import com.chensen.opensource.interf.OnTabReselectedListener;

/**
 * author：chensen on 2016/12/13 15:55
 * desc：
 */

public class TweetViewPagerFragment extends BaseFragment implements OnTabReselectedListener{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tweet;
    }

    @Override
    public void onTabReselected() {

    }
}

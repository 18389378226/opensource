package com.chensen.opensource.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.chensen.opensource.R;
import com.chensen.opensource.common.base.BaseActivity;
import com.chensen.opensource.interf.OnTabReselectedListener;
import com.chensen.opensource.main.nav.NavFragment;
import com.chensen.opensource.main.nav.NavigationButton;

/**
 * author：chensen on 2016/12/13 10:46
 * desc：
 */

public class MainActivity extends BaseActivity implements NavFragment.OnNavigationReselectListener {
    private NavFragment mNavBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        FragmentManager manager = getSupportFragmentManager();
        mNavBar = (NavFragment) manager.findFragmentById(R.id.fag_nav);
        mNavBar.setup(this, manager, R.id.fl_container, this);


    }

    @Override
    public void onReselect(NavigationButton navigationButton) {
        Fragment fragment = navigationButton.getFragment();
        if (fragment != null && fragment instanceof OnTabReselectedListener) {
            OnTabReselectedListener listener = (OnTabReselectedListener) fragment;
            listener.onTabReselected();
        }


    }
}

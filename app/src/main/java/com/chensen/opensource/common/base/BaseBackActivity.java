package com.chensen.opensource.common.base;

import android.support.v7.app.ActionBar;

/**
 * author：chensen on 2016/12/13 10:34
 * desc：
 */

public abstract class BaseBackActivity extends BaseActivity {

    @Override
    protected void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}

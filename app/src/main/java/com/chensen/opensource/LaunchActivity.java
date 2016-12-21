package com.chensen.opensource;

import android.content.Intent;

import com.chensen.opensource.common.base.BaseActivity;
import com.chensen.opensource.main.MainActivity;

/**
 * author：chensen on 2016/12/8 14:46
 * desc：启动页
 */

public class LaunchActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }



    @Override
    protected void initData() {
        findViewById(R.id.app_start_view).postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        }, 1000);

    }


    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

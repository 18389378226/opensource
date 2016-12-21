package com.chensen.opensource.common.base;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewStub;

import com.chensen.opensource.R;
import com.chensen.opensource.common.view.TitleBar;

/**
 * author：chensen on 2016/12/15 14:58
 * desc：
 */

public abstract class BaseTitleFragment extends BaseFragment {

    TitleBar mTitleBar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_basetitle;
    }

    @Override
    protected void onBindViewBefore(View root) {
        super.onBindViewBefore(root);

        // on before onBindViewBefore call
        ViewStub stub = (ViewStub) root.findViewById(R.id.vs_content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();
    }


    @Override
    protected void initView(View root) {
        super.initView(root);

        // not null
        mTitleBar = (TitleBar) root.findViewById(R.id.nav_title_bar);
        mTitleBar.setTitle(getTitleRes());
        mTitleBar.setIcon(getIconRes());
        mTitleBar.setOnIconClickListener(getIconClickListener());
    }

    protected abstract
    @LayoutRes
    int getContentLayoutId();

    protected abstract
    @StringRes
    int getTitleRes();

    protected
    @DrawableRes
    int getIconRes() {
        return 0;
    }

    protected View.OnClickListener getIconClickListener() {
        return null;
    }
}

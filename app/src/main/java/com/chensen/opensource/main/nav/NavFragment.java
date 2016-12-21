package com.chensen.opensource.main.nav;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;

import net.qiujuer.genius.ui.drawable.shape.BorderShape;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.chensen.opensource.R;
import com.chensen.opensource.common.base.BaseFragment;
import com.chensen.opensource.main.fragment.ExploreFragment;
import com.chensen.opensource.main.fragment.NewsFragment;
import com.chensen.opensource.main.fragment.TweetViewPagerFragment;
import com.chensen.opensource.main.fragment.UserFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.attr.x;
import static com.chensen.opensource.R.id.nb_news;

/**
 * author：chensen on 2016/12/13 10:59
 * desc：
 */

public class NavFragment extends BaseFragment {
    @BindView(nb_news)
    NavigationButton nbNews;
    @BindView(R.id.nb_tweet)
    NavigationButton nbTweet;
    @BindView(R.id.iv_tweet)
    ImageView ivTweet;
    @BindView(R.id.nb_explore)
    NavigationButton nbExplore;
    @BindView(R.id.nb_me)
    NavigationButton nbMe;

    private int mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationButton mCurrentNavButton;
    private OnNavigationReselectListener mOnNavigationReselectListener;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        ShapeDrawable lineDrawable = new ShapeDrawable(new BorderShape(new RectF(0, 1, 0, 0)));
        lineDrawable.getPaint().setColor(getResources().getColor(R.color.list_divider_color));

        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new ColorDrawable(getResources().getColor(R.color.white)),
                lineDrawable});
        root.setBackground(layerDrawable);


        nbNews.init(R.drawable.tab_icon_new,
                R.string.main_tab_name_news,
                NewsFragment.class);

        nbTweet.init(R.drawable.tab_icon_tweet,
                R.string.main_tab_name_tweet,
                TweetViewPagerFragment.class);

        nbExplore.init(R.drawable.tab_icon_explore,
                R.string.main_tab_name_explore,
                ExploreFragment.class);

        nbMe.init(R.drawable.tab_icon_me,
                R.string.main_tab_name_my,
                UserFragment.class);


    }

    public void setup(Context context, FragmentManager fragmentManager, int contentId, OnNavigationReselectListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(nbNews);
    }

    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    @OnClick({nb_news, R.id.nb_tweet, R.id.iv_tweet, R.id.nb_explore, R.id.nb_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case nb_news:
                NavigationButton nbNews = (NavigationButton) view;
                doSelect(nbNews);
                break;
            case R.id.nb_tweet:
                NavigationButton nbTweet = (NavigationButton) view;
                doSelect(nbTweet);
                break;
            case R.id.iv_tweet:
                break;
            case R.id.nb_explore:
                NavigationButton nbExplore = (NavigationButton) view;
                doSelect(nbExplore);
                break;
            case R.id.nb_me:
                NavigationButton nvMe = (NavigationButton) view;
                doSelect(nvMe);
                break;
        }
    }

    private void doSelect(NavigationButton newNavButton) {
        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }

        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }

        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(mContext, newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();
    }


    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }

    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }
}

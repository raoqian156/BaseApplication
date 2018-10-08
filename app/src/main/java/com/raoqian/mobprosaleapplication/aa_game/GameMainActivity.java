package com.raoqian.mobprosaleapplication.aa_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.aa_game.fragment.GameBaseFragment;
import com.raoqian.mobprosaleapplication.base.BaseActivity;
import com.raoqian.mobprosaleapplication.aa_game.fragment.GameMainFragment;
import com.raoqian.mobprosaleapplication.aa_game.fragment.GameSettingFragment;
import com.raoqian.mobprosaleapplication.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class GameMainActivity extends BaseActivity implements View.OnClickListener {

    private static final int[] tabId = new int[]{
            R.id.activity_tab1
            , R.id.activity_tab2
            , R.id.activity_tab3
    };
    TextView[] bottomBTN = new TextView[tabId.length];
    ViewPager viewPager;
    List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
        StatusBarUtil.setTransparentForImageView(this, null);
        initView();
        findViewById(R.id.start).setOnClickListener(this);
    }

    GameMainFragment mGameMainFragment = new GameMainFragment();
    GameSettingFragment mGameSettingFragment = new GameSettingFragment();
    GameBaseFragment mGameBaseFragment = new GameBaseFragment();

    private void initView() {
        mFragments.add(mGameMainFragment);
        mFragments.add(mGameSettingFragment);
        mFragments.add(mGameBaseFragment);
        viewPager = findViewById(R.id.viewpager);
        for (int i = 0; i < tabId.length; i++) {
            bottomBTN[i] = findViewById(tabId[i]);
            bottomBTN[i].setOnClickListener(this);
        }
        viewPager.setOffscreenPageLimit(tabId.length);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setSelect(0);
    }

    MyViewPagerAdapter mAdapter;

    public void createPerson(View view) {
        WuQiOld wuQiOld = mGameBaseFragment.getmWuQiOld();
        ZhuangBei zhuangBei = mGameBaseFragment.getmZhuangBei();

        mGameSettingFragment.setWuQiOld(wuQiOld);
        mGameSettingFragment.setZhuangBei(zhuangBei);

        mGameMainFragment.setPerson1(mGameSettingFragment.getPerson1());
        mGameMainFragment.setPerson2(mGameSettingFragment.getPerson2());

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            createPerson(v);
            return;
        }
        for (int i = 0; i < tabId.length; i++) {
            if (v.getId() == tabId[i]) {
                setSelect(i);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSelected() != viewPager.getCurrentItem()) {
            setSelect(viewPager.getCurrentItem());
        }
    }

    public void setSelect(int select) {
        viewPager.setCurrentItem(select);
        for (int i = 0; i < bottomBTN.length; i++) {
            bottomBTN[i].setSelected(i == select);
        }
    }

    private int getSelected() {
        for (int i = 0; i < bottomBTN.length; i++) {
            if (bottomBTN[i].isSelected()) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//按返回键不退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return false;
    }

    private class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        List<Fragment> mFragments;

        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> mFragments) {
            super(fm);
            this.mFragments = mFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}

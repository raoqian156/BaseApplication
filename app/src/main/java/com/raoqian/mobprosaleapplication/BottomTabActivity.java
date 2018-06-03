package com.raoqian.mobprosaleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.raoqian.mobprosaleapplication.base.BaseActivity;
import com.raoqian.mobprosaleapplication.ui.HomeFragment;
import com.raoqian.mobprosaleapplication.ui.MessageFragment;
import com.raoqian.mobprosaleapplication.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class BottomTabActivity extends BaseActivity implements View.OnClickListener {

    private static final int[] tabId = new int[]{
            R.id.activity_tab1
            , R.id.activity_tab2
            , R.id.activity_tab3
            , R.id.activity_tab4
    };
    TextView[] bottomBTN = new TextView[tabId.length];
    ViewPager viewPager;
    List<Fragment> mFragments = new ArrayList<>();
    TextView unReadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        StatusBarUtil.setTransparentForImageView(this, null);
        initView();
    }

    private void initView() {
        mFragments.add(new MessageFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new MessageFragment());
        unReadView = findViewById(R.id.unread_msg_number);
        viewPager = findViewById(R.id.viewpager);
        for (int i = 0; i < tabId.length; i++) {
            bottomBTN[i] = findViewById(tabId[i]);
            bottomBTN[i].setOnClickListener(this);
        }
        viewPager.setOffscreenPageLimit(tabId.length);
        MyViewPagerAdapter mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mFragments);
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


    @Override
    public void onClick(View v) {
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

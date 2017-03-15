package com.zrx.live.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zrx.live.R;
import com.zrx.live.fragment.HomeFragment;
import com.zrx.live.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.content)
    ViewPager content;
    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindView(R.id.rbMine)
    RadioButton rbMine;
    @BindView(R.id.rgTools)
    RadioGroup rgTools;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private FragmentPagerAdapter mAdapter;

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        initView();
    }


    private void initView(){
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(MineFragment.newInstance());
        mAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        content.setAdapter(mAdapter);
    }



    @OnClick({R.id.rbHome, R.id.rbMine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                content.setCurrentItem(0);
                break;
            case R.id.rbMine:
                content.setCurrentItem(1);
                break;
        }
    }
}

package com.example.hjb.nickdemo.nickfragment.picture;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hjb.nickdemo.R;

/**
 * Created by hjb on 2017/4/23.
 */

public class PictureAllActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    String deviceId;
    ViewPager mViewPager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_all_layout);
        deviceId = getIntent().getStringExtra("deviceID");
        initView();
        initEvent();
    }
    private void initView(){
        mTabLayout = (TabLayout)findViewById(R.id.photoTabLayout);
        mViewPager= (ViewPager)findViewById(R.id.viewPager);
        String[] tabTitles = {"时间", "人物", "地点", "上传者"};
        PhotoPagerAdapter pagerAdapter = new PhotoPagerAdapter(getSupportFragmentManager(),tabTitles,deviceId);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    private void initEvent() {
        TextView textView = (TextView)findViewById(R.id.select);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PictureAllActivity","click");
                    PhotoListFragment fragment = (PhotoListFragment) ((PhotoPagerAdapter) mViewPager.getAdapter()).getFragment(mViewPager.getCurrentItem());
                    fragment.showSelectIcon();
            }
        });

    }
}

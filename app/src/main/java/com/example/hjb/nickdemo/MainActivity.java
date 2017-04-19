package com.example.hjb.nickdemo;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjb.nickdemo.nickfragment.DeviceFragment;
import com.example.hjb.nickdemo.nickfragment.PictureFragment;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Fragment currentFragment;
    private DeviceFragment deviceFragment;
    private PictureFragment pictureFragment;
    private ImageView deviceIv,picIv,nickIv,userIv;
    private TextView deviceTv,picTv,nickTv,userTv;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        fragmentManager = getSupportFragmentManager();
        initIv();

    }
    private void initIv(){
        //获取所有控件
        deviceIv =(ImageView) findViewById(R.id.deviceIv);
        deviceIv.setOnClickListener(this);
        picIv = (ImageView)findViewById(R.id.picIv);
        picIv.setOnClickListener(this);
        nickIv = (ImageView)findViewById(R.id.nickIv);
        userIv =(ImageView)findViewById(R.id.userIv);
        deviceTv = (TextView) findViewById(R.id.deviceTv);
        picTv = (TextView)findViewById(R.id.picTv);
        nickTv = (TextView)findViewById(R.id.nickTv);
        userTv = (TextView)findViewById(R.id.userTv);

        //设置初始状态。
        deviceIv.setImageResource(R.drawable.nick_main_device_blue);
        deviceTv.setTextColor(getResources().getColor(R.color.colorNickTextBlue));
        Log.d("MainActivity","y");
        //fragmentManager.beginTransaction().add(R.id.realFragment, new DeviceFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.deviceIv:
                if(deviceFragment==null){
                    deviceFragment=new DeviceFragment();
                }
                setWhenDeviceClicked(deviceFragment);
                break;
            case R.id.picIv:
                break;
            case R.id.nickIv:
                break;
            case R.id.userIv:
                break;
        }
    }
    public void setWhenDeviceClicked(Fragment fragment){
        Log.d("MainActivity","yesssss");
        if(pictureFragment==null){
            pictureFragment=new PictureFragment();
        }
        if(fragment instanceof DeviceFragment){
            Log.d("MainActivity","yes");
        }

        if(deviceFragment==null){
            deviceFragment=new DeviceFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), deviceFragment);

        // 设置底部tab变化
        deviceIv.setImageResource(R.drawable.nick_main_device_blue);
        deviceTv.setTextColor(getResources().getColor(R.color.colorNickTextBlue));
        picIv.setImageResource(R.drawable.nick_main_pic_black);
        picTv.setTextColor(getResources().getColor(R.color.colorNickTextLowBlack));
        nickIv.setImageResource(R.drawable.nick_main_nick_black);
        nickTv.setTextColor(getResources().getColor(R.color.colorNickTextLowBlack));
        userIv.setImageResource(R.drawable.nick_main_user_black);
        userTv.setTextColor(getResources().getColor(R.color.colorNickTextLowBlack));
    }




    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.realFragment, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }
}

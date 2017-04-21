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
import com.example.hjb.nickdemo.nickfragment.NickFragment;
import com.example.hjb.nickdemo.nickfragment.PictureFragment;
import com.example.hjb.nickdemo.nickfragment.UserSetFragment;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Fragment currentFragment;
    private DeviceFragment deviceFragment;
    private PictureFragment pictureFragment;
    private NickFragment nickFragment;
    private UserSetFragment userSetFragment;
    private ImageView deviceIv,picIv,nickIv,userIv;
    private TextView deviceTv,picTv,nickTv,userTv;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //初始化所有控件，并设置device为第一个为初始界面
        initIv();
        setdefault();
    }
    private void initIv(){
        //获取所有控件
        deviceIv =(ImageView) findViewById(R.id.deviceIv);
        deviceIv.setOnClickListener(this);
        picIv = (ImageView)findViewById(R.id.picIv);
        picIv.setOnClickListener(this);
        nickIv = (ImageView)findViewById(R.id.nickIv);
        nickIv.setOnClickListener(this);
        userIv =(ImageView)findViewById(R.id.userIv);
        userIv.setOnClickListener(this);
        deviceTv = (TextView) findViewById(R.id.deviceTv);
        picTv = (TextView)findViewById(R.id.picTv);
        nickTv = (TextView)findViewById(R.id.nickTv);
        userTv = (TextView)findViewById(R.id.userTv);

        //设置初始状态。
        deviceIv.setImageResource(R.drawable.nick_main_device_blue);
        deviceTv.setTextColor(getResources().getColor(R.color.colorNickTextBlue));

        //fragmentManager.beginTransaction().add(R.id.realFragment, new DeviceFragment()).commit();
    }
    private void setdefault(){
        if(deviceFragment==null){
            deviceFragment=new DeviceFragment();
        }
        fragmentManager = getSupportFragmentManager();
        currentFragment=deviceFragment;
        fragmentManager.beginTransaction().add(R.id.realFragment,deviceFragment).commit();
    }
    //设置点击事件
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
                if(pictureFragment==null){
                    pictureFragment=new PictureFragment();
                }
                setWhenDeviceClicked(pictureFragment);
                break;
            case R.id.nickIv:
                if(nickFragment==null){
                    nickFragment=new NickFragment();
                }
                setWhenDeviceClicked(nickFragment);
                break;
            case R.id.userIv:
                if(userSetFragment==null){
                    userSetFragment=new UserSetFragment();
                }
                setWhenDeviceClicked(userSetFragment);
                break;
        }
    }
    //设置点击事件响应
    public void setWhenDeviceClicked(Fragment fragment){

        deviceIv.setImageResource(R.drawable.nick_main_device_black);
        deviceTv.setTextColor(getResources().getColor(R.color.colorNickTextLowBlack));
        picIv.setImageResource(R.drawable.nick_main_pic_black);
        picTv.setTextColor(getResources().getColor(R.color.colorNickTextLowBlack));
        nickIv.setImageResource(R.drawable.nick_main_nick_black);
        nickTv.setTextColor(getResources().getColor(R.color.colorNickTextLowBlack));
        userIv.setImageResource(R.drawable.nick_main_user_black);
        userTv.setTextColor(getResources().getColor(R.color.colorNickTextLowBlack));
        if(fragment instanceof DeviceFragment){

            deviceIv.setImageResource(R.drawable.nick_main_device_blue);
            deviceTv.setTextColor(getResources().getColor(R.color.colorNickTextBlue));
        }else if(fragment instanceof PictureFragment){
            picIv.setImageResource(R.drawable.nick_main_pic_blue);
            picTv.setTextColor(getResources().getColor(R.color.colorNickTextBlue));
        }else if(fragment instanceof NickFragment){
            nickIv.setImageResource(R.drawable.nick_main_nick_black);
            nickTv.setTextColor(getResources().getColor(R.color.colorNickTextBlue));
        }else if(fragment instanceof UserSetFragment){
            userIv.setImageResource(R.drawable.nick_main_user_blue);
            userTv.setTextColor(getResources().getColor(R.color.colorNickTextBlue));
        }
        addOrShowFragment(fragmentManager.beginTransaction(), fragment);

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

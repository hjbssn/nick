package com.example.hjb.nickdemo.nickfragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hjb.nickdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjb on 2017/4/19.
 */

public class DeviceFragment extends Fragment {
    private List<DeviceSituation> device = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.device_layout, container, false);
        initView();
        initRecyclerView(view);
        return view;
    }

    private void initView(){
        device.add(new DeviceSituation(R.drawable.device_rlv_imglearn,true));
        device.add(new DeviceSituation(R.drawable.device_rlv_imglearn,false));
        device.add(new DeviceSituation(R.drawable.nick_main_device_blue,false));
        device.add(new DeviceSituation(R.drawable.device_rlv_imglearn,false));
    }
    //初始化整个fragment的recyclerview
    public void initRecyclerView(View view){

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.deviceRecycleListView);
        //获取线性布局管理器，使用getActivity的获取上下文
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局
        recyclerView.setLayoutManager(layoutManager);
        //设置适配器
        DeviceAdapter adapter = new DeviceAdapter(device);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyItemDecoration());
    }
    class MyItemDecoration extends RecyclerView.ItemDecoration {
        /**
         *
         * @param outRect 边界
         * @param view recyclerView ItemView
         * @param parent recyclerView
         * @param state recycler 内部数据管理
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //设定底部边距为1px
            outRect.set(0, 0, 0, 3);
        }
    }
}

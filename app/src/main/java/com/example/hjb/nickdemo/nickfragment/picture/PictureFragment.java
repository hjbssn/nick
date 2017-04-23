package com.example.hjb.nickdemo.nickfragment.picture;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjb.nickdemo.R;
import com.example.hjb.nickdemo.model.NKDeviceInfoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hjb on 2017/4/19.
 */

public class PictureFragment extends Fragment {

    ImageView allPicIv;
    ImageView albumIv;
    Button allPic;
    Button album;

    MyDevAdapter adapter;

    ExpandableListView dev_expandablelistview;

    List<NKDeviceInfoModel> devices;

    Map<String, List<NKDeviceInfoModel>> map = null;

    String deviceID;

    List<String> parent = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pictures_main_layout, container, false);
        dev_expandablelistview = (ExpandableListView)view.findViewById(R.id.dev_expandablelistview);
        allPic = (Button)view.findViewById(R.id.allPic);
        album = (Button)view.findViewById(R.id.album);
        allPicIv = (ImageView)view.findViewById(R.id.allPicIv);
        albumIv = (ImageView)view.findViewById(R.id.albumIv);
        initData();
        updateDeviceList(devices);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dev_expandablelistview.collapseGroup(0);
            }
        });
        return view;
    }



    public void initData() {
        devices = new ArrayList<>();
        for(int i=0;i<5;i++) {
            NKDeviceInfoModel nkDeviceInfoModel = new NKDeviceInfoModel();
            nkDeviceInfoModel.setDeviceName("device"+i);
            nkDeviceInfoModel.setId(""+(char)(i+'a'));
            Log.d("PictureFragment",nkDeviceInfoModel.getId());
            devices.add(nkDeviceInfoModel);
        }
        if (devices != null && devices.size() != 0) {
            parent = new ArrayList<String>();
            parent.add(devices.get(0).getDeviceName());
            map = new HashMap<String, List<NKDeviceInfoModel>>();
            List<NKDeviceInfoModel> list1 = new ArrayList<NKDeviceInfoModel>();
            list1.addAll(devices);
            map.put(devices.get(0).getDeviceName(), list1);
            deviceID = devices.get(0).getId();
        }

    }
    private void setDefaultList(String string){
        if(string.equals("a")){

        }else {
            Log.d("PictureFragment",string);
            allPicIv.setImageResource(R.drawable.let_go_btn_normal);
        }
    }
    private void setDefaultList(){
        //allPicIv.setImageResource(R.drawable.let_go_btn_normal);
    }

    public void updateDeviceList(List<NKDeviceInfoModel> value) {
        devices = value;
        adapter = new MyDevAdapter();
        dev_expandablelistview.setAdapter(adapter);
        dev_expandablelistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                adapter.changeHeader(devices.get(childPosition).getDeviceName());
                //presenter.getDefaultPhotoes(devices.get(childPosition).getId());
                setDefaultList(devices.get(childPosition).getId());
                Log.d("pictureFragment","k:"+devices.get(childPosition).getId());
                deviceID = devices.get(childPosition).getId();
                dev_expandablelistview.collapseGroup(groupPosition);
                return true;
            }
        });
        setDefaultList();
        if (devices != null && devices.size() > 0) {
            Log.d("pictureFragment","s:"+devices.get(0).getId());
            setDefaultList(devices.get(0).getId());
        }
        adapter.notifyDataSetChanged();
    }

    class MyDevAdapter extends BaseExpandableListAdapter {

        //得到子item需要关联的数据
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = parent.get(groupPosition);
            return (map.get(key).get(childPosition));
        }

        //得到子item的ID
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //设置子item的组件
        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            String key = PictureFragment.this.parent.get(groupPosition);
            NKDeviceInfoModel info = map.get(key).get(childPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.picture_dev_child, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.dev_children_textview);
            tv.setText(info.getDeviceName());
            return convertView;
        }

        //获取当前父item下的子item的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = parent.get(groupPosition);
            int size = map.get(key).size();
            return size;
        }

        //获取当前父item的数据
        @Override
        public Object getGroup(int groupPosition) {
            return parent.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            if (parent != null) {
                return parent.size();
            } else {
                return 0;
            }

        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //设置父item组件
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.picture_dev_parent, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.dev_parent_textview);
            ImageView indicator = (ImageView) convertView.findViewById(R.id.dev_indicator);
            if (isExpanded) {
                indicator.setImageResource(R.drawable.tongyong_xiangshangjiantou);
            } else {
                indicator.setImageResource(R.drawable.tongyong_xiangxiajiantou);
            }
            tv.setText(PictureFragment.this.parent.get(groupPosition));
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public void changeHeader(String header) {
            parent.clear();
            parent.add(header);
            map.clear();
            map.put(header, devices);
            notifyDataSetChanged();
        }

    }


}

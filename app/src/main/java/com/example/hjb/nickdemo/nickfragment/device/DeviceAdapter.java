package com.example.hjb.nickdemo.nickfragment.device;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.hjb.nickdemo.R;

import java.util.List;

/**
 * Created by hjb on 2017/4/19.
 */

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {
    List<DeviceSituation> mDeviceList;

    public DeviceAdapter(List<DeviceSituation> mDeviceList){this.mDeviceList=mDeviceList;}

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView picImage;
        Switch aSwitch;
        Context getcontext ;
        public ViewHolder(View view){
            super(view);
            picImage = (ImageView) view.findViewById(R.id.deviceRlvImage);
            aSwitch = (Switch)view.findViewById(R.id.deviceRlvSwitch);
            getcontext = view.getContext();
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //getcontext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_rlv_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DeviceSituation device = mDeviceList.get(position);
        holder.picImage.setImageResource(device.getImageId());
        holder.aSwitch.setChecked(device.getAtLine());
        //Glide.with(getcontext).load(R.drawable.ic_launcher).into(holder.picImage);
        //Glide.with(holder.getcontext).load(pic.getImageId()).into(holder.picImage);
        //Glide.with(holder.getcontext).load("http://pic.kekenet.com/Article/UploadFiles/200806/20080604091500311.jpg").into(holder.picImage);
    }

    @Override
    public int getItemCount() {
        return mDeviceList.size();
    }

}

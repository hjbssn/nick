package com.example.hjb.nickdemo.nickfragment.picture;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjb.nickdemo.R;


/**
 * Author:yaocl
 * Created on 2016/7/14.
 */
public class NKPhotoListHolder extends RecyclerView.ViewHolder{

    CheckBox selectAllIcon;

    ImageView avatar;

    ImageView photo;

    CheckBox mCheckBox;

    TextView title;

    public NKPhotoListHolder(View itemView) {
        super(itemView);
        selectAllIcon = (CheckBox)itemView.findViewById(R.id.selectAllCheckbox);
        avatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
        photo = (ImageView) itemView.findViewById(R.id.photo);
        mCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        title = (TextView) itemView.findViewById(R.id.title);

    }

    public void setCheckBoxEnable(boolean enable){
        if (enable) {
            mCheckBox.setVisibility(View.VISIBLE);
        }else {
            mCheckBox.setVisibility(View.GONE);
        }
    }

    public void setSelectAllEnable(boolean enable) {
        if (enable) {
            selectAllIcon.setVisibility(View.VISIBLE);
        }else {
            selectAllIcon.setVisibility(View.GONE);
        }
    }

    public void setTextHeader(){
        avatar.setVisibility(View.GONE);
    }

    public void setAvatarHeader(){
        avatar.setVisibility(View.VISIBLE);
    }
}

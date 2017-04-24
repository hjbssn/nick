package com.example.hjb.nickdemo.nickfragment.pictureview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.hjb.nickdemo.R;
import com.example.hjb.nickdemo.model.NKUserModel;
import com.example.hjb.nickdemo.nickfragment.picture.NKPhotoBean;
import com.example.hjb.nickdemo.nickfragment.picture.NKPhotoCategoryBean;
import com.example.hjb.nickdemo.nickfragment.picture.NKPhotoHeader;

import java.util.ArrayList;
import java.util.List;



/**
 * Author:yaocl
 * Created on 2016/6/17.
 */
public class NKPhotoViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private NKUserModel mUserModel;
    private String mDeviceId;
    private List<NKPhotoCategoryBean> mPhotos;
    private List<NKPhotoBean> mPhotoList = new ArrayList<>();


    public NKPhotoViewPagerAdapter(Context context, List<NKPhotoCategoryBean> photos,String deviceId) {
        mContext = context;
        mPhotos = photos;
        mUserModel = new NKUserModel(context);
        mDeviceId = deviceId;
        for (int i = 0; i < mPhotos.size(); i++) {
            mPhotoList.addAll(mPhotos.get(i).getPhotoes());
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mPhotoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       /* for (int i = 0; i < container.getChildCount(); i++) {
            if (container.getChildAt(i) instanceof PhotoView) {
                Glide.clear(container.getChildAt(i));
                ((PhotoView) container.getChildAt(i)).setImageDrawable(null);
            }
        }*/
        container.removeView((View) object);
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        photoView.enable();
        photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        NKPhotoHeader header = new NKPhotoHeader(mUserModel.getUserName(),mUserModel.getToken(),mDeviceId,mPhotoList.get(position).getMediaId());
        //NKGlideProxy.getInstance().loadURLWithAuthCover(mContext,mPhotoList.get(position).getPhotoURL(),photoView,header);
        Log.d("NKPhotoViewPagerAdapter",""+position);
        Glide.with(mContext).load(mPhotoList.get(position).getPhotoURL())
                .thumbnail(0.5f)
                .dontAnimate()
                .override(300, 300)
                .placeholder(R.drawable.nicklogo)
                .into(photoView);
        container.addView(photoView);
        return photoView;
    }

    public NKPhotoBean getCurrentPhoto(int currentPosition){
        return mPhotoList.get(currentPosition);
    }

    public void removePhoto(int currentPosition){
        mPhotoList.remove(currentPosition);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
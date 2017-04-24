package com.example.hjb.nickdemo.nickfragment.pictureview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.hjb.nickdemo.R;
import com.example.hjb.nickdemo.consts.IAlbumConst;
import com.example.hjb.nickdemo.consts.IIntentExtraConsts;
import com.example.hjb.nickdemo.nickfragment.picture.NKPhotoCategoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class NKPhotoShowActivity extends AppCompatActivity implements INKPhotoShow.View {

    private static final int ALBUM_DELETE_PHOTO = 0xa3;

    public static final int NO_PHOTO_SIZE = 1;
    private INKPhotoShow.Presenter mPresenter;
    private NKPhotoViewPagerAdapter mAdapter;
    private String deviceId;
    private String albumId;


    /*照片viewpager*/
    @Bind(R.id.photoViewPager)
    ViewPager mPhotoViewPager;

    @Bind(R.id.delete)
    LinearLayout delete;

    @Bind(R.id.download)
    LinearLayout download;

    @Bind(R.id.addToAlbum)
    LinearLayout addToAlbum;

    @Bind(R.id.addToAlbumRelativeLayout)
    RelativeLayout addToAlbumRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加-----
        setContentView(R.layout.activity_nkphoto_show);
        //-------
        if(getIntent().getStringExtra(IIntentExtraConsts.IS_ALBUM).equals(IAlbumConst.IS_ALBUM)){
            addToAlbumRelativeLayout.setVisibility(View.GONE);
        }
        albumId = getIntent().getStringExtra(IIntentExtraConsts.ALBUM_ID);


        List<NKPhotoCategoryBean> mPhotos = getIntent().getParcelableArrayListExtra(IIntentExtraConsts.PHOTO);
        deviceId = getIntent().getStringExtra(IIntentExtraConsts.DEVICE_ID);

        //mPresenter = new NKPhotoViewPresenter(this,deviceId);

        int CurrentPosition = getIntent().getIntExtra(IIntentExtraConsts.CURRENTPHOTO_POSITION,0);
        mAdapter = new NKPhotoViewPagerAdapter(this,mPhotos,deviceId);
        mPhotoViewPager = (ViewPager)findViewById(R.id.photoViewPager);
        mPhotoViewPager.setAdapter(mAdapter);
        mPhotoViewPager.setCurrentItem(CurrentPosition);
    }


    /*@Override
    protected int setLayoutId() {
        return R.layout.activity_nkphoto_show;
    }*/

    @OnClick(R.id.delete)
    public void onDeleteClick(){
        if(getIntent().getStringExtra(IIntentExtraConsts.IS_ALBUM).equals(IAlbumConst.IS_ALBUM)){
           List<String> list =  new ArrayList<String>();
            list.add(mAdapter.getCurrentPhoto(mPhotoViewPager.getCurrentItem()).getMediaId());
            mPresenter.deleteNormalAlbumPhoto(list,albumId);
        }else{
            String mediaId = mAdapter.getCurrentPhoto(mPhotoViewPager.getCurrentItem()).getMediaId();
            mPresenter.deletePhotos(mediaId);
        }


    }

    @OnClick(R.id.download)
    public void onDownloadClick(){
        showMessage("等待");
        //mPresenter.downloadPhotos(getString(R.string.dowloadSuccess),getString(R.string.dowloadDefeat),mAdapter.getCurrentPhoto(mPhotoViewPager.getCurrentItem()));
    }

    /*@OnClick(R.id.addToAlbum)
    public void onAddToAlbumClick(){
        Intent intent = new Intent(this, NKPhotoToAlbumActivity.class);
        intent.putExtra(IIntentExtraConsts.DEVICE_ID,deviceId);
        List<String> photo = new ArrayList<>();
        photo.add(mAdapter.getCurrentPhoto(mPhotoViewPager.getCurrentItem()).getMediaId());
        intent.putStringArrayListExtra(IIntentExtraConsts.PHOTO, (ArrayList<String>) photo);
        startActivity(intent);
    }*/

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showNoPhotoPage() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showPhotoPage() {
        mAdapter.removePhoto(mPhotoViewPager.getCurrentItem());
    }

    @Override
    public void showDeleteResult() {
        switch (mAdapter.getCount()){
            case NO_PHOTO_SIZE:
                showNoPhotoPage();
                break;
            default:
                showPhotoPage();
        }
    }

    @Override
    public void showMessage(String message) {

    }
}

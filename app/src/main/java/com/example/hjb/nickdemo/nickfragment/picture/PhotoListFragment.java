package com.example.hjb.nickdemo.nickfragment.picture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.hjb.nickdemo.R;
import com.example.hjb.nickdemo.consts.IAlbumConst;
import com.example.hjb.nickdemo.consts.IIntentExtraConsts;
import com.example.hjb.nickdemo.nickfragment.pictureview.NKPhotoShowActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjb on 2017/4/23.
 */

public class PhotoListFragment extends Fragment{


    private static final String PARAM_POSITION = "position";
    private static final String PARAM_DEVICEID = "deviceId";

    private GridLayoutManager mGridLayoutManager;
    private NKPhotoListAdapter mPhotoListAdapter;

    private int position;
    private String deviceId;
    private View rootView;
    ScrollChildRefreshLayout refreshLayout;
    NKPhotoListAdapter.photoClickListener mPhotoClickListener = new NKPhotoListAdapter.photoClickListener() {
        @Override
        public void onPhotoItemClick(int currentPosition, List<NKPhotoCategoryBean> mPhotos) {
            Intent intent = new Intent(getContext(), NKPhotoShowActivity.class);
            intent.putParcelableArrayListExtra(IIntentExtraConsts.PHOTO, (ArrayList) mPhotos);
            intent.putExtra(IIntentExtraConsts.DEVICE_ID,deviceId);
            intent.putExtra(IIntentExtraConsts.CURRENTPHOTO_POSITION,currentPosition);
            intent.putExtra(IIntentExtraConsts.IS_ALBUM, IAlbumConst.IS_NOT_ALBUM);
            startActivity(intent);
        }
    };

    RecyclerView photoList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("PhotoListFragment","Oncreate");
        position = getArguments().getInt(PARAM_POSITION);
        deviceId = getArguments().getString(PARAM_DEVICEID);

        mPhotoListAdapter = new NKPhotoListAdapter(getContext(),new ArrayList<NKPhotoCategoryBean>(0),deviceId, position);
        super.onCreate(savedInstanceState);

    }
    public void initrecyclerView(View view){
        refreshLayout = (ScrollChildRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setScrollUpChild(photoList);
        photoList = (RecyclerView) view.findViewById(R.id.photoList);
        mGridLayoutManager = new GridLayoutManager(getContext(),4);
        photoList.setLayoutManager(mGridLayoutManager);
        mPhotoListAdapter.setLayoutManager(mGridLayoutManager);
        mPhotoListAdapter.setPhotoClickListener(mPhotoClickListener);
        photoList.setAdapter(mPhotoListAdapter);
        Log.d("PhotoListFragment","init 结束");
    }
    public void initDataTest(){
        List<NKPhotoCategoryBean> list = new ArrayList<>(0);
        List<NKPhotoBean> lists = new ArrayList<>(0);
        NKPhotoCategoryBean a=new NKPhotoCategoryBean();
        NKPhotoBean b = new NKPhotoBean();
        NKPhotoBean c = new NKPhotoBean();
        b.setThumbURL("http://img01.sogoucdn.com/app/a/100540002//854097_s_90_2_219x160.jpg");
        b.setPhotoURL("http://img01.sogoucdn.com/app/a/100540002//854097_s_90_2_219x160.jpg");
        c.setThumbURL("http://img01.sogoucdn.com/app/a/100540002//1066280_s_90_2_219x160.jpg");
        c.setPhotoURL("http://img01.sogoucdn.com/app/a/100540002//1066280_s_90_2_219x160.jpg");
        lists.add(b);
        lists.add(c);
        a.setDate("1996-11-1");
        a.setLocation("dlut");
        a.setPerson("hjb");
        a.setPhotoes(lists);
        a.setUploader("hjb;s mm");
        /*Parcel parcel = Parcel.obtain();
        parcel.writeString("dlut");
        parcel.writeString("hjb");
        parcel.writeString("1996-11-1");
        parcel.writeString("hjb's mm");

        list.add(new NKPhotoCategoryBean(parcel));*/
        list.add(a);
        mPhotoListAdapter.replaceData(list);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.d("PhotoListFragment","OncreateView");
        rootView = inflater.inflate(R.layout.picture_grid_layout,container,false);
        initDataTest();
        initrecyclerView(rootView);
        return rootView;

    }

    public static Fragment newInstance(int position, String deviceId){
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putString("deviceID",deviceId);
        fragment.setArguments(bundle);
        return fragment;
    }
    public void onDestroyView() {
        //Log.d("PhotoListFragment", "Fragment "+position+" destroyed");
        super.onDestroyView();
        unbindDrawables(rootView.findViewById(R.id.swipeRefreshLayout));
    }
    private void unbindDrawables(View view) {
        if (view.getBackground() != null)
        {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView))
        {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++)
            {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    public void showSelectIcon() {
        NKPhotoListAdapter listAdapter = (NKPhotoListAdapter) photoList.getAdapter();
        listAdapter.setChooseable(true);
        Log.d("PhotoListFragment","showcselectIcon");
    }
}

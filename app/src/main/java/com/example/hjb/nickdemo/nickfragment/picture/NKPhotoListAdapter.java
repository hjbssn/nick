package com.example.hjb.nickdemo.nickfragment.picture;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.example.hjb.nickdemo.R;
import com.example.hjb.nickdemo.model.NKUserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:yaocl
 * Created on 2016/6/15.
 */
public class NKPhotoListAdapter extends NKPhotoSelectAndSectionedAdapter<NKPhotoListHolder>{

    private Context mContext;
    private NKUserModel mUserModel;

    private String mDeviceId;
    private int mPosition;

    private photoClickListener mPhotoClickListener;
    private boolean isChooseable = false;
    private int[] actualDimensions;
    //private final RequestManager glide;

    private List<NKPhotoCategoryBean> mPhotos;

    public NKPhotoListAdapter(Context context,List<NKPhotoCategoryBean> photos, String deviceId, int positon) {
        mContext = context;
        mPhotos = photos;
        mUserModel = new NKUserModel(context);
        mDeviceId = deviceId;
        mPosition = positon;
        Log.d("NKPhotoListAdapter","构造函数");
    }

    private void setPhotos(List<NKPhotoCategoryBean> photos) {
        mPhotos = photos;
    }

    @Override
    public int getSectionCount() {
        return mPhotos.size();
    }

    @Override
    public int getItemCount(int section) {
        return mPhotos.get(section).getPhotoes().size();
       // return ;
    }

    @Override
    public void onBindHeaderViewHolder(final NKPhotoListHolder holder, final int section) {

        Log.d("NKPhotoListAdapter","onBindHeaderViewHolder");

        holder.selectAllIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = holder.selectAllIcon.isChecked();
                toggleSectionSelected(section,isChecked);
            }
        });

        holder.setSelectAllEnable(isChooseable);
        switch (mPosition){
            case 0:
                holder.setTextHeader();
                holder.title.setText(mPhotos.get(section).getDate());
                break;
            case 1:
                holder.setAvatarHeader();
                holder.title.setText(mPhotos.get(section).getPerson());
                break;
            case 2:
                holder.setTextHeader();
                holder.title.setText(mPhotos.get(section).getLocation());
                break;
            case 3:
                holder.setTextHeader();
                holder.title.setText(mPhotos.get(section).getUploader());
                break;
        }
        if (getSectionSelectedIndices() != null) {
            boolean sectionSelected = isSectionSelected(section);
            holder.selectAllIcon.setChecked(sectionSelected);
        }
    }

    @Override
    public void onBindViewHolder(final NKPhotoListHolder holder, final int section, int relativePosition, final int absolutePosition) {
        //Log.d("NKPhotoListAdapter","onBindViewHolder");
        NKPhotoBean photo = mPhotos.get(section).getPhotoes().get(relativePosition);

        //加载带校验的图片
        NKPhotoHeader nkPhotoHeader = new NKPhotoHeader(mUserModel.getUserName(), mUserModel.getToken(), mDeviceId, photo.getMediaId());
        //NKGlideProxy.getInstance().loadURLWithAuthCover(glide,photo.getThumbURL(),holder.photo,nkPhotoHeader);
        Glide.with(mContext).load(photo.getThumbURL())
                .thumbnail(0.5f)
                .dontAnimate()
                .override(300, 300)
                .placeholder(R.drawable.nicklogo)
                .into(holder.photo);
        //设置mCheckbox是否显示
        holder.mCheckBox.setOnCheckedChangeListener(null);
        if (getSelectedIndices() !=null){
            boolean selected = isSelected(absolutePosition);
            holder.mCheckBox.setChecked(selected);
            holder.setCheckBoxEnable(selected);
        }else {
            holder.mCheckBox.setChecked(false);
        }

        //处理photo的点击事件
        if (isChooseable){
            holder.photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = holder.mCheckBox.isChecked();
                    if (checked) {
                        holder.mCheckBox.setVisibility(View.GONE);
                        holder.mCheckBox.setChecked(false);
                    }else {
                        holder.mCheckBox.setVisibility(View.VISIBLE);
                        holder.mCheckBox.setChecked(true);
                    }
                }
            });

            holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    toggleSelected(section,absolutePosition);
                }
            });
        }else {
            holder.photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPhotoClickListener.onPhotoItemClick(absolutePosition,mPhotos);
                }
            });
        }
    }


    @Override
    public NKPhotoListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d("NKPhotoListAdapter","onCreateViewHolder");
        final View view = LayoutInflater.from(mContext)
                .inflate(viewType == VIEW_TYPE_HEADER ? R.layout.layout_photo_grid_header : R.layout.layout_photo_grid_item,parent,false);

        //测量item的大小，为预加载提供数据
        if (actualDimensions == null){
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    if (actualDimensions == null) {
                        actualDimensions = new int[]{view.getWidth(),view.getHeight()};
                    }
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
        }
        return  new NKPhotoListHolder(view);
    }


    /**
     * 设置图片点击监听器
     * @param photoClickListener
     */
    public void setPhotoClickListener(photoClickListener photoClickListener) {
        mPhotoClickListener = photoClickListener;
    }

    /**
     * 更新列表数据
     * @param photos
     */
    public void replaceData(List<NKPhotoCategoryBean> photos){
       setPhotos(photos);
       notifyDataSetChanged();
    }

    /**
     * 变更选择模式
     * @param chooseable
     */
    public void setChooseable(boolean chooseable) {
        isChooseable = chooseable;
        if (!chooseable) {
            clearSelected();
        }
        notifyDataSetChanged();
    }


    /**
     * 当View回收时取消Glide对ImageView的引用
     * @param holder
     */
    @Override
    public void onViewRecycled(NKPhotoListHolder holder) {
        Log.d("photoRecyclerView", "ViewRecycled");
        if (holder.photo != null) {
            Glide.clear(holder.photo);
            holder.photo.setImageDrawable(null);
        }
        super.onViewRecycled(holder);
    }

    public List<String> getSelectPhotos() {
        Integer[] selectedIndices = getSelectedIndices();
        List<String> mediaIds = new ArrayList<>();
        for (int i = 0; i < selectedIndices.length; i++) {
            int[] sectionAndRelativePosition = getSectionAndRelativePosition(selectedIndices[i]);
            mediaIds.add(mPhotos.get(sectionAndRelativePosition[0]).getPhotoes().get(sectionAndRelativePosition[1]).getMediaId());
        }

        return mediaIds;
    }

    public List<NKPhotoBean> getDownloadUrlPhotos() {
        Integer[] selectedIndices = getSelectedIndices();
        List<NKPhotoBean> urls = new ArrayList<>(selectedIndices.length);
        for (int i = 0; i < selectedIndices.length; i++) {
            int[] sectionAndRelativePosition = getSectionAndRelativePosition(selectedIndices[i]);
            urls.add(mPhotos.get(sectionAndRelativePosition[0]).getPhotoes().get(sectionAndRelativePosition[1]));
        }

        return urls;
    }

    //图片点击查看事件监听接口
    interface photoClickListener {
        void onPhotoItemClick(int currentPosition, List<NKPhotoCategoryBean> photoes);
    }

    /**
     * 更新本类别的图片信息
     */
    public void deleteRefresh(){
        Integer[] selectedIndices = getSelectedIndices();
        for (int i = selectedIndices.length -1; i > -1; i--) {
            int[] sectionAndRelativePosition = getSectionAndRelativePosition(selectedIndices[i]);
            mPhotos.get(sectionAndRelativePosition[0]).getPhotoes().remove(sectionAndRelativePosition[1]);

            List<NKPhotoBean> sectionPhoto = mPhotos.get(sectionAndRelativePosition[0]).getPhotoes();
            if (sectionPhoto.size() == 0) {
                mPhotos.remove(sectionAndRelativePosition[0]);
            }
        }
        clearSelected();
    }

    public void selectAllPhotos(){
        List<Integer> selectIndicates = new ArrayList<>();
        List<Integer> sectionIndices = new ArrayList<>();
        SparseArray<Integer> sectionCurrentSize = new SparseArray<>();
        for (int i = 0; i < mPhotos.size(); i++) {
            sectionIndices.add(i);
            sectionCurrentSize.put(i,getItemCount(i));
        }
        for (int i = 0; i < getAllItemCount(); i++) {
            selectIndicates.add(i);
        }
        setSelectAll(selectIndicates,sectionIndices,sectionCurrentSize);
    }

    public int getAllItemCount(){
        int allItemCount = 0;
        for (int i = 0; i < getSectionCount(); i++) {
            allItemCount = allItemCount + getItemCount(i);
        }
        return allItemCount;
    }


}

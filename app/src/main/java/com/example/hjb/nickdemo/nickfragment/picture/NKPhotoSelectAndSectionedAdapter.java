package com.example.hjb.nickdemo.nickfragment.picture;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author:yaocl
 * Created on 2016/7/5.
 */
public abstract class NKPhotoSelectAndSectionedAdapter<VH extends RecyclerView.ViewHolder> extends SectionedRecyclerViewAdapter<VH>{

    //选中图片
    private List<Integer> mSelectedIndices;

    //头部是否全选
    private List<Integer> mSectionIndices;

    private SparseArray<Integer> mSectionCurrentSize;

    public NKPhotoSelectAndSectionedAdapter() {
        mSectionCurrentSize = new SparseArray<>();
        mSectionIndices = new ArrayList<>();
        mSelectedIndices = new ArrayList<>();
    }

    /**
     * 取消全选
     */
    public final void clearSelected(){
        mSelectedIndices.clear();
        mSectionIndices.clear();
        mSectionCurrentSize.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取选择的图片数量
     */
    public final int getSelectCount(){
        return mSelectedIndices.size();
    }

    /**
     * 获取选择的图片
     */
    public final Integer[] getSelectedIndices(){
        /*if(mSelectedIndices==null||mSelectedIndices.size()==1){
            return null;
        }*/
        Collections.sort(mSelectedIndices);
        return mSelectedIndices.toArray(new Integer[mSelectedIndices.size()]);
    }

    /**
     * 判断图片是否是选中
     */
    public final boolean isSelected(Integer index){
        return mSelectedIndices.contains(index);
    }

    /**
     * 判断头部是否全选
     */
    public final boolean isSectionSelected(int sectionIndex){
        return mSectionIndices.contains(sectionIndex);
    }

    /**
     * 获取选择头部
     */
    public final Integer[] getSectionSelectedIndices(){
        Collections.sort(mSectionIndices);
        return mSectionIndices.toArray(new Integer[mSectionIndices.size()]);
    }

    /**
     * 变换分组选择状态
     */
    public final void toggleSectionSelected(int sectionIndex,boolean isChecked){
        int[] range = getSectionItemRange(sectionIndex);
        for (int i = range[0]; i < range[1]; i++) {
            if (isChecked&&!mSelectedIndices.contains(i)) {
                changeItemSelectedState(sectionIndex,i);
            }else if(!isChecked&&mSelectedIndices.contains(i)){
                changeItemSelectedState(sectionIndex,i);
            }
        }
        changeSectionSelectedState(sectionIndex);
        notifyItemRangeChanged(getRealIndexOfSection(sectionIndex)+1,getItemCount(sectionIndex));
    }


    /**
     * 变换单个选择状态和分组选择状态
     * @param sectionIndex
     * @param index
     */
    public final void toggleSelected(int sectionIndex, int index){
        int currentSize = changeItemSelectedState(sectionIndex, index);

        if (currentSize == getItemCount(sectionIndex)){
            mSectionIndices.add(sectionIndex);
            onSectionSelectAll(getRealIndexOfSection(sectionIndex));
        }else if (mSectionIndices.contains(sectionIndex)){
            mSectionIndices.remove((Integer) sectionIndex);
            onSectionNoSelectAll(getRealIndexOfSection(sectionIndex));
        }
    }

    /**
     * 更改单个图片的选择信息
     * @param sectionIndex
     * @param absoluteIndex
     * @return
     */
    private int changeItemSelectedState(int sectionIndex, int absoluteIndex){
        int sectionSelectedSize = mSectionCurrentSize.get(sectionIndex) == null ? 0:mSectionCurrentSize.get(sectionIndex);

        if (mSelectedIndices.contains(absoluteIndex)) {
            mSelectedIndices.remove((Integer) absoluteIndex);
            sectionSelectedSize = sectionSelectedSize - 1;
            mSectionCurrentSize.put(sectionIndex,sectionSelectedSize);
        }else {
            mSelectedIndices.add(absoluteIndex);
            sectionSelectedSize = sectionSelectedSize + 1;
            mSectionCurrentSize.put(sectionIndex,sectionSelectedSize);
        }
        return sectionSelectedSize;
    }

    /**
     * 全选分组的选择信息
     * @param sectionIndex
     */
    private void changeSectionSelectedState(int sectionIndex){
        if (mSectionIndices.contains(sectionIndex)){
            mSectionIndices.remove((Integer) sectionIndex);
        }else {
            mSectionIndices.add(sectionIndex);
        }
    }

    private void onSectionSelectAll(int sectionIndex) {
        notifyItemChanged(sectionIndex);
    }

    private void onSectionNoSelectAll(int sectionIndex) {
        notifyItemChanged(sectionIndex);
    }

    /**
     * 获取头部在List中的真正位置
     * @param sectionIndex
     * @return
     */
    private int getRealIndexOfSection(int sectionIndex){
        int realPosition = 0;
        for (int s = 0; s < sectionIndex; s++) {
            realPosition = realPosition + getItemCount(s) + 1;
        }
        return realPosition;
    }

    /**
     * 返回对应分组的起始item绝对位置和item个数
     * @param sectionIndex
     * @return
     */
    private int[] getSectionItemRange(int sectionIndex){
        int[] range = new int[2];
        int itemCount = 0;
        for (int s = 0; s < sectionIndex; s++) {
            itemCount = itemCount + getItemCount(s);
        }
        range[0] = itemCount;
        range[1] = itemCount+getItemCount(sectionIndex);
        return range;
    }

    /**
     * 根据绝对位置获取相对位置和组位置
     * @param absolutePosition
     * @return
     */
    public int[] getSectionAndRelativePosition(int absolutePosition){
        int lastSectionIndex = 0;
        int relativePosition = absolutePosition+1;//当前位置之前的item个数
        for (int i = 0; relativePosition > getItemCount(i); i++) {
            relativePosition = relativePosition - getItemCount(i);
            lastSectionIndex = lastSectionIndex + 1;
        }
        return new int[]{lastSectionIndex,relativePosition - 1};
    }


    public void setSelectAll(List<Integer> selectedIndices, List<Integer> sectionIndices, SparseArray<Integer> sectionCurrentSize) {
        mSelectedIndices = selectedIndices;
        mSectionIndices = sectionIndices;
        mSectionCurrentSize = sectionCurrentSize;
        notifyDataSetChanged();
    }

}


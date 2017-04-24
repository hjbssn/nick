package com.example.hjb.nickdemo.nickfragment.picture;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Created by hjb on 2017/4/23.
 */

public class PhotoPagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabTitles;
    private String mDeviceId;
    private SparseArray<Fragment> fragments = new SparseArray<>();

    public PhotoPagerAdapter(FragmentManager fm, String[] tabTitles , String deviceId) {
        super(fm);
        this.tabTitles = tabTitles;
        mDeviceId = deviceId;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        Log.d("PhotoPagerAdapter","DO IT REAL");
        Fragment fragment = PhotoListFragment.newInstance(position, mDeviceId);
//        fragments.add(fragment);
        return fragment;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragments.put(position,fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fragments.remove(position);
        super.destroyItem(container, position, object);
    }

    /**
     * 返回TabLayout的标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position % tabTitles.length];
    }

    public Fragment getFragment(int position){
        return fragments.get(position);
    }


}

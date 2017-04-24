package com.example.hjb.nickdemo.nickfragment.picture;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.hjb.nickdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:yaocl
 * Created on 2016/8/12.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {


    private Context mContext;
    private List<String> testUrls = new ArrayList<>();

    public TestAdapter(Context context) {
        mContext = context;
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//1066280_s_90_2_219x160.jpg");
        testUrls.add("http://bizhi.sogou.com/detail/info/1053472");
        testUrls.add("http://bizhi.sogou.com/detail/info/771634");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//854097_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//771717_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//857519_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//857528_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//879335_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//879338_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//879339_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//879337_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//879336_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//918437_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//918443_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//994861_s_90_2_219x160.jpg");
        testUrls.add("http://img01.sogoucdn.com/app/a/100540002//516137_s_90_2_219x160.jpg");
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate( R.layout.layout_photo_grid_item,parent,false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(TestHolder holder, int position) {
        String url = testUrls.get(position);
        //NKGlideProxy.getInstance().loadImage(mContext,url,holder.mImageView);
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.nicklogo)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return testUrls.size();
    }


    static class TestHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;

        public TestHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.photo);
        }
    }
}

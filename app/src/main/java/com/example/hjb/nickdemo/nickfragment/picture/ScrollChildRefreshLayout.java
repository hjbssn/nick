package com.example.hjb.nickdemo.nickfragment.picture;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hjb on 2017/4/24.
 */

public class ScrollChildRefreshLayout extends SwipeRefreshLayout {
    private View mScrollUpChild;

    public ScrollChildRefreshLayout(Context context) {
        super(context);
    }

    public ScrollChildRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        if (mScrollUpChild != null) {
            return ViewCompat.canScrollVertically(mScrollUpChild, -1);
        }
        return super.canChildScrollUp();
    }

    public void setScrollUpChild(View view) {
        mScrollUpChild = view;
    }
}

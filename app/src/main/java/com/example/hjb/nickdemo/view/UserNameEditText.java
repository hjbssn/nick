package com.example.hjb.nickdemo.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.hjb.nickdemo.R;

/**
 * Created by hjb on 2017/4/18.
 */

public class UserNameEditText extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {
    public UserNameEditText(Context context) {
        this(context,null);
    }

    public UserNameEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public UserNameEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        //initDrawable(context,attrs,defStyleAttr);
    }
    private void initDrawable(Context context, AttributeSet attrs, int defStyleAttr) {

    }
    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;


    private void init() {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
//        	throw new NullPointerException("You can add drawableRight attribute in XML");
            mClearDrawable = getResources().getDrawable(R.drawable.nick_login_error);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认设置隐藏图标
        setClearIconVisible(false);
        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    //里面写上自己想做的事情，也就是DrawableRight的触发事件
                    this.setText("");
                    try {

                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }

                }
            }
        }
        return super.onTouchEvent(event);
    }
    /*
        @Override
        protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
            this.hasFoucs = focused;
            if (this.hasFoucs) {
                setClearIconVisible(getText().length() > 0);
            } else {
                setClearIconVisible(false);
            }
        }
    */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }


    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        //如果你想让它一直显示DrawableRight的图标，并且还需要让它触发事件，直接把null改成mClearDrawable即可
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }
//后面的代码无需更改，只需要粘贴进去即可，如果有不需要的可以删除，当然不删除也不会出错。

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count,
                              int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
}

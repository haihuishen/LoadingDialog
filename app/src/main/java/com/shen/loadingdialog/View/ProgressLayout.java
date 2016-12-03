package com.shen.loadingdialog.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.shen.loadingdialog.R;

/**
 * 自定义，进度布局
 *
 */
public class ProgressLayout extends FrameLayout {

    private static final int DEFAULT_COUNT = 5;		// 默认数
    private int spotsCount;							// "点"个数

    public ProgressLayout(Context context) {
        this(context, null);
    }

    public ProgressLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)   // 指使用该注解的方法适用于  系统版本  为3.0及以上系统的手机
    public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    	// TypedArray ： 控件或布局中属性的"数组/集合"
    	// 获取所有的"属性"
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Dialog,						// 自定义的"属性"
                defStyleAttr, defStyleRes);

        // 获取布局中："点个数"的属性值： 没有就设置默认值
        spotsCount = a.getInt(R.styleable.Dialog_DialogSpotCount, DEFAULT_COUNT);
        a.recycle();
    }

    /**
     *  得到这个自定义布局，有多少个点
     * @return
     */
    public int getSpotsCount() {
        return spotsCount;
    }
}

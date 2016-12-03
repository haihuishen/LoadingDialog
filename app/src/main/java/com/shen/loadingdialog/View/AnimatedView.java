package com.shen.loadingdialog.View;

import android.content.Context;
import android.view.View;

/**
 * extends View<p>
 * 
 * "点" 控件/布局
 * 
 */
class AnimatedView extends View {

    private int target;			// 目标值

    public AnimatedView(Context context) {
        super(context);
    }

    public float getXFactor() {
        return getX() / target;
    }

    public void setXFactor(float xFactor) {
        setX(target * xFactor);
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }
}

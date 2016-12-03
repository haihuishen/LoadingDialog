package com.shen.loadingdialog.View;

import android.view.animation.Interpolator;

/**
 * Interpolator用于动画中的时间插值，其作用就是把0到1的浮点值变化映射到另一个浮点值变化。
 */
class HesitateInterpolator implements Interpolator {

    private static double POW = 1.0/2.0;  // 0.5 ==> 1/2

    
    /**
     * Math.pow(底数,几次方)
     * 如：double a=2.0;
     *     double b=3.0;
     * double c=Math.pow(a,b);
     * 就是2的三次方是多少；
     * c最终为8；
     */
    
    @Override
    public float getInterpolation(float input) {
        return input < 0.5
                ? (float) Math.pow(input * 2, POW) * 0.5f
                : (float) Math.pow((1 - input) * 2, POW) * -0.5f + 1;
    }
    // input 值得取值范围： 0~1
    
//    说到这个input的值，我觉得有不少朋友可能会联想到我们在“中”篇文章中使用过的fraction值。
//    那么这里的input和fraction有什么关系或者区别呢？
//    答案很简单，input的值决定了fraction的值。
//    input的值是由系统经过计算后传入到getInterpolation()方法中的，
//    然后我们可以自己实现getInterpolation()方法中的算法，根据input的值来计算出一个返回值
//    ，而这个返回值就是fraction了。

// 因此，最简单的情况就是input值和fraction值是相同的，这种情况由于input值是匀速增加的，因而fraction的值也是匀速增加的，所以动画的运动情况也是匀速的
}
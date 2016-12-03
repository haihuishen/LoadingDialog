package com.shen.loadingdialog.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

/**
 * 动画播放方式(动画管理器：开关之类的)<p>
 * 要我们设置动画过程进去(构造函数就传递"动画过程数组"进去)
 * 
 */
class AnimatorPlayer extends AnimatorListenerAdapter {

    private boolean interrupted = false;		// 间断;中断
    
    /**
     * Animator:这是类的超类提供基本支持动画可以开始,结束,AnimatorListeners添加。
     */
    private Animator[] animators;

    /**
     * 构造函数
     * @param animators	动画数组
     */
    public AnimatorPlayer(Animator[] animators) {
        this.animators = animators;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (!interrupted) 		// 如果 interrupted == false (false：不中断)进入
        	animate();
    }

    /**
     * 开始动画
     */
    public void play() {
        animate();
    }

    /**
     * 中断标志设为：true，就是要中断动画
     */
    public void stop() {
        interrupted = true;
    }

    /**
     * 有两种不同的方法来增加动画AnimatorSet:
     * 要么playTogether()或playSequentially()方法可以调用添加一组动画,
     * 或玩(动画)可以结合使用方法构建器类中添加动画一个接一个。
     * 
     * 可以建立一个AnimatorSet与循环依赖之间的动画。
     * 例如,可以建立一个动画a1之前开始动画a2、a3之前a2,a3 a1。
     * 这个配置的结果是未定义的,但是通常会导致没有影响动画播放。
     * 因为这个(因为循环依赖不逻辑意义),应避免循环依赖,依赖动画只能向一个方向流动。
     * 
     */
    private void animate() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animators);	// 添加一组动画!
        set.addListener(this);			// 给动画添加，本动画监听适配器
        set.start();					// 开启动画
    }
}

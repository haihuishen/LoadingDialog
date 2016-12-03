package com.shen.loadingdialog.View;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.shen.loadingdialog.R;


/**
 * 
 * 
 */
public class SpotsDialog extends AlertDialog {

    private static final int DELAY = 150;			// delay 	延时
    private static final int DURATION = 1500;		// duration 持续

    private int size;								// 有多少个点
    private AnimatedView[] spots;					// "点" 控件/布局
    private AnimatorPlayer animator;				// 动画播放方式
    private CharSequence message;					// char数组类型吧

    public SpotsDialog(Context context) {
    	// 使用 res/values/style.xml 自定义的窗口主题样式(其实继承了...)
        this(context, R.style.SpotsDialogDefault);	  // 这个this代表下面的 有 int theme
    }

    public SpotsDialog(Context context, CharSequence message) {
        this(context);
        this.message = message;
    }

    public SpotsDialog(Context context, CharSequence message, int theme) {
        this(context, theme);
        this.message = message;
    }

    public SpotsDialog(Context context, int theme) {
        super(context, theme);
    }

    public SpotsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dmax_spots_dialog);     // 窗口的布局样式
        
        // dialog.setCancelable(false);
        // dialog弹出后会点击屏幕或物理返回键，dialog不消失
        // dialog.setCanceledOnTouchOutside(false);
        // dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
        setCanceledOnTouchOutside(false);

        initMessage();
        initProgress();
    }

    @Override
    protected void onStart() {
        super.onStart();

        animator = new AnimatorPlayer(createAnimations());
        animator.play();		// 开始动画
    }

    @Override
    protected void onStop() {
        super.onStop();

        animator.stop();		// 动画管理器就会关掉(其实是设置了标志)
    }

    // 设置"对话框"的内容
    @Override
    public void setMessage(CharSequence message) {
        ((TextView) findViewById(R.id.dmax_spots_title)).setText(message);
    }

    /**
     * 对话框的内容;构造函数时候传递进来的
     */
    private void initMessage() {
        if (message != null && message.length() > 0) {
            ((TextView) findViewById(R.id.dmax_spots_title)).setText(message);
        }
    }

    /**
     * 初始化进度布局
     */
    private void initProgress() {
        ProgressLayout progress = (ProgressLayout) findViewById(R.id.dmax_spots_progress);
        size = progress.getSpotsCount();

        spots = new AnimatedView[size];			// 有多少个点
        
        // 自定义的像素值
        int size = getContext().getResources().getDimensionPixelSize(R.dimen.spot_size);	
        int progressWidth = getContext().getResources().getDimensionPixelSize(R.dimen.progress_width);
        
        for (int i = 0; i < spots.length; i++) {
            AnimatedView v = new AnimatedView(getContext());		
            v.setBackgroundResource(R.drawable.dmax_spots_spot);		// 点控件"背景"
            v.setTarget(progressWidth);
            v.setXFactor(-1f);											// 设置x轴？移动
            progress.addView(v, size, size);							// 将控件设置到，布局中
            spots[i] = v;												
        }
    }

    /**
     * 为每个"点"设置动画，将点的动画数组返回
     * @return
     */
    private Animator[] createAnimations() {
        Animator[] animators = new Animator[size];			// 有多少个点，就有多少个动画
        
        for (int i = 0; i < spots.length; i++) {			// spots：点(点控件)  也是根据 size初始化的来的
            
        	// 第一个参数用于指定这个动画要操作的是哪个控件
        	// 第二个参数用于指定这个动画要操作这个控件的哪个属性
        	// 第三个参数是可变长参数，这个就跟ValueAnimator中的可变长参数的意义一样了，
        	// 就是指这个属性值是从哪变到哪。像我们上面的代码中指定的就是将textview的alpha属性从0变到1再变到0；
        	// 这里是，"xFactor"横坐标  由0变到1
        	Animator move = ObjectAnimator.ofFloat(spots[i], "xFactor", 0, 1);
            move.setDuration(DURATION);								// 动画持续时间
            move.setInterpolator(new HesitateInterpolator());		// 控件做什么运动(匀速，加速等)
            move.setStartDelay(DELAY * i);							// 设置延时开始
            animators[i] = move;									// 动画交给别人管理(AnimatorPlayer)
        }
        return animators;
    }
}

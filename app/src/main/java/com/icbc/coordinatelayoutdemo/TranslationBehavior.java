package com.icbc.coordinatelayoutdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * 备注：红橙Darren --> Material Design - 自定义Behavior
 * https://www.jianshu.com/p/f3c81cf485e5
 */
public class TranslationBehavior extends FloatingActionButton.Behavior {

    public TranslationBehavior(Context context, AttributeSet attributeSet){
        super(context,attributeSet);

    }
    /**
     *  当coordinatorLayout的子view试图开始嵌套滚动的时候被调用，当返回true的时候表明
     *  coordinatorLayout 充当nested scroll parent处理这次滑动，这里需要注意的是只有当返回为true的时候，
     *  Behavior才能收到后面一些事件的回调，比如onNestedPreScroll、onNestedScroll等
     *  这里有个重要的参数就是nestedScrollAxes ，表示处理滑动的方向
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target

     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }
    /**
     * 正在滚动中
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed    已经消费的x方向的距离
     * @param dyConsumed    已经消费的y方向的距离
     * @param dxUnconsumed  x方向剩余的滚动距离
     * @param dyUnconsumed  y方向剩余的滚动距离
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed > 0) {
            float value=((CoordinatorLayout.LayoutParams)child.getLayoutParams()).bottomMargin+child.getMeasuredHeight();
            child.animate().translationY(value).setDuration(500).start();
        } else {
            child.animate().translationY(0).setDuration(500).start();
        }
    }
}

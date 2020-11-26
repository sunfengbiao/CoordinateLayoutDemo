package com.icbc.coordinatelayoutdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * 备注：红橙Darren --> Material Design - 自定义Behavior
 * https://www.jianshu.com/p/f3c81cf485e5
 */
public class TranslationBottomBehavior extends BottomSheetBehavior {

    private boolean mIsCrouch;

    public TranslationBottomBehavior(Context context, AttributeSet attributeSet){
        super(context,attributeSet);

    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes==ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0) {
            if(!mIsCrouch){
                float value=child.getMeasuredHeight();
                child.animate().translationY(value).setDuration(500).start();
                mIsCrouch=!mIsCrouch;
            }

        } else {
            if(mIsCrouch){
                child.animate().translationY(0).setDuration(500).start();
                mIsCrouch=!mIsCrouch;
            }

        }
    }
}

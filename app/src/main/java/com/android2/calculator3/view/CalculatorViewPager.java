

package com.android2.calculator3.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CalculatorViewPager extends ViewPager {
    private boolean enabled;

    public CalculatorViewPager(Context context) {
        this(context, null);
    }

    public CalculatorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    /**
     * ViewPager inherits ViewGroup's default behavior of delayed clicks on its
     * children, but in order to make the calc buttons more responsive we
     * disable that here.
     */
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if(this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getPagingEnabled() {
        return enabled;
    }
}

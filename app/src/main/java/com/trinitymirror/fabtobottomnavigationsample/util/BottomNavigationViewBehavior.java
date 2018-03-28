package com.trinitymirror.fabtobottomnavigationsample.util;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {


    public interface Callback {
        void onSlideUp();

        void onSlideDown();
    }

    private @NonNull
    final Callback callback;

    public BottomNavigationViewBehavior(@NonNull Callback callback) {
        this.callback = callback;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       BottomNavigationView child, @NonNull
                                               View directTargetChild, @NonNull View target,
                                       int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child,
                               @NonNull View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed,
                               @ViewCompat.NestedScrollType int type) {
        if (dyConsumed > 0) {
            callback.onSlideUp();
        } else if (dyConsumed < 0) {
            callback.onSlideDown();
        }
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
    }
}

package com.englishtopass.englishtopassapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovableFloatingActionButton extends FloatingActionButton implements View.OnTouchListener {
    private static final String TAG = "MovableFloatingActionBu";
    private static final float CLICK_DRAG_TOLERANCE = 10;

    private float downRawX, downRawY;
    private float dX, dY;

    public MovableFloatingActionButton(Context context) {
        super(context);
        init();
    }

    public MovableFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MovableFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnTouchListener(this);



    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)v.getLayoutParams();


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                // 364
                downRawX = event.getRawX();

                // 1883
                downRawY = event.getRawY();

                /**
                 *
                 * getY() is the position of the view inside its parent
                 * rawY() is the postion on the whole screen so i we dont take away the difference it will try to set the
                 * position of the button off screen and it would start Yaxis from the top of its parent.
                 *
                 */

                //120
                dX = v.getX() - downRawX;

                //1300 - 1883 = -530
                dY = v.getY() - downRawY;

                return true;

            case MotionEvent.ACTION_MOVE:

                View view = (View) v.getParent();

                int parentHeight = view.getHeight();
                int parentWidth = view.getWidth();

                // 154
                int viewWidth = v.getWidth();

                // 154
                int viewHeight = v.getHeight();


                // The higher the button goes the X/Y argument (INT) goes up/down, the margin is 16 so until X/Y hits 16dp Math will return the movable position -
                float newX = event.getRawX() + dX;
                newX = Math.max(layoutParams.leftMargin, newX); // Don't allow the FAB past the left hand side of the parent
                newX = Math.min(parentWidth - viewWidth - layoutParams.rightMargin, newX); // Don't allow the FAB past the right hand side of the parent

                // The higher the button goes the X/Y argument (INT) goes up/down, the margin is 16 so until X/Y hits 16dp Math will return the movable position -
                float newY = event.getRawY() + dY;
                newY = Math.max(layoutParams.topMargin, newY); // Don't allow the FAB past the top of the parent
                newY = Math.min(parentHeight - viewHeight - layoutParams.bottomMargin, newY); // Don't allow the FAB past the bottom of the parent

                // Simulates moving the view -
                v.animate()
                        .x(newX)
                        .y(newY)
                        .setDuration(0)
                        .start();

                return true; // Consumed

            case MotionEvent.ACTION_UP:
                float upRawX = event.getRawX();
                float upRawY = event.getRawY();

                float upDX = upRawX - downRawX;
                float upDY = upRawY - downRawY;

                if (Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CLICK_DRAG_TOLERANCE) { // A click
                    return performClick();
                }
                else { // A drag
                    return true; // Consumed
                }

        }

        return false;

    }

}

package com.englishtopass.englishtopassapplication.CustomViews;

import android.animation.Animator;
import android.content.Context;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class SettingsFloatingActionButton extends FloatingActionButton {
    private static final String TAG = "MovableFloatingActionBu";



    public SettingsFloatingActionButton(Context context) {
        super(context);
        init();
    }

    public SettingsFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SettingsFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {}


}




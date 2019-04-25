package com.englishtopass.englishtopassapplication.CustomViews;

import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Interfaces.SetScrollYListener;

public class LinkMovementMethodCustom extends LinkMovementMethod {
    private static final String TAG = "CustomLinkMovementMetho";

    private static LinkMovementMethodCustom sInstance;
    private SetScrollYListener setScrollYListener;

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer,
                                MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {

            setScrollYListener.setSpanYPosition((int) event.getRawY());

        }

        return super.onTouchEvent(widget, buffer, event);
    }


    public static LinkMovementMethodCustom getInstance() {

        if (sInstance == null) {

            sInstance = new LinkMovementMethodCustom();

        }

        return sInstance;
    }

    public void setSetScrollYListener(SetScrollYListener setScrollYListener) {
        this.setScrollYListener = setScrollYListener;
    }
}

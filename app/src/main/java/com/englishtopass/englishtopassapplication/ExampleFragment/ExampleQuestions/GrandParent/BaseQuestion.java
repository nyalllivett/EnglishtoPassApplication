package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.GrandParent;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

public class BaseQuestion extends Fragment implements View.OnClickListener {
    private static final String TAG = "BaseQuestionFragment";

    /**
     *
     *  QUESTION TYPE
     *  1 multiple cloze
     *  2 open cloze
     *  3 keyword transformation
     *  4 word formation
     *
     */

    protected int questionType;
    protected boolean engaged;

    protected Chronometer chronometer;

    public BaseQuestion() {
        // Required empty public constructor
    }

    public static BaseQuestion newInstance() {
        BaseQuestion fragment = new BaseQuestion();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        engaged = false;

    }

    protected void startTimer() {

        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.start();

    }

    protected void endTimer() {

        chronometer.stop();

    }

    protected SpannableStringBuilder searchAndSetSpans(String bodyOfText) {

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(bodyOfText);

        String keyword = "______";

        int startingIndex = bodyOfText.indexOf(keyword);

        while (startingIndex > 0) {

            stringBuilder.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {


                    Log.d(TAG, "onClick: hello");

                }

            }, startingIndex, keyword.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            startingIndex = bodyOfText.indexOf(keyword, startingIndex + keyword.length());

        }


        return stringBuilder;
    }


    @Override
    public void onClick(View v) {



    }
}

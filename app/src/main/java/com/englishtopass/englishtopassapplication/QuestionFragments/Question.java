package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.englishtopass.englishtopassapplication.CustomViews.ChronometerCustom;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.disposables.CompositeDisposable;

public class Question extends Fragment {
    private static final String TAG = "Question";
    private String[] correctAnswers;


    /** CHRONOMETER
     * timer
     */
    protected ChronometerCustom chronometer;


    /** COMPOSITE DISPOSABLE
     * disposable pot to dispose of any base calls from children.
     */
    protected CompositeDisposable compositeDisposable;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        compositeDisposable = new CompositeDisposable();

    }

    /**
     * starts the timer if there is any time in the db plus the time from the view model.
     * the collection of time is only used the first time the stop watch is started
     */
    protected void startTimer() {

        chronometer.start();

    }


    // QUESTION METHOD
    protected void endTimer() {
        Log.d(TAG, "endTimer: here");
        chronometer.stop();

    }



    /**
     * returns the correct answers for the question
     * called from sqGetCheckedAnswerBooleans() just before finishing the test
     * @return
     * String array
     */
    public String[] qGetCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * sets the correct answers for the current question. Should be able to use this
     * for all types of question.
     * called from onSuccess in the RXJava call when retrieving the current question
     * @param correctAnswers
     */
    public void qSetCorrectAnswers(String[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public void onDestroy() {

        compositeDisposable.dispose();

        super.onDestroy();

    }
}

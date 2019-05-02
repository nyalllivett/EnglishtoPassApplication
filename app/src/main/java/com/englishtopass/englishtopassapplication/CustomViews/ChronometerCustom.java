package com.englishtopass.englishtopassapplication.CustomViews;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableCompletableObserver;

public class ChronometerCustom extends Chronometer {
    private static final String TAG = "CustomChronometer";

    CompositeDisposable compositeDisposable;
    public ChronometerCustom(Context context) {
        super(context);
        init();
    }

    public ChronometerCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChronometerCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ChronometerCustom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
    }



    public void showTime() {
        if (getAlpha() != 0) return;
        animate().alpha(1f).start();
        startCompletable();
    }


    @Override
    public void start() {
        super.start();
//        startCompletable();

    }

    public void setBaseTime(long timePassed) {

        Log.d(TAG, "setBaseTime: time passed" + timePassed);

        this.setBase(SystemClock.elapsedRealtime() - timePassed);
    }

    public long returnElapsedTime() {

        Log.d(TAG, "returnElapsedTime: here" + (SystemClock.elapsedRealtime() - getBase() / 1000));


        return (SystemClock.elapsedRealtime() - getBase());


    }

    private void hideTime() {

        animate().alpha(0f).start();

    }

    public void startCompletable(){

        Completable.timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        hideTime();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }
                });
    }
}

package com.englishtopass.englishtopassapplication.CustomViews;

import android.content.Context;
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
    }

    public ChronometerCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChronometerCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ChronometerCustom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void showTime() {

        if (getAlpha() != 0) return;

        animate().alpha(1f).start();

        startCompletable();

    }

    @Override
    public void start() {
        super.start();

        startCompletable();

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
                        Log.d(TAG, "onComplete: complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }
                });

    }

}

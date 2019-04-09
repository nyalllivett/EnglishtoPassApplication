package com.englishtopass.englishtopassapplication.CustomViews;

import android.animation.Animator;
import android.content.Context;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.util.Log;

import com.englishtopass.englishtopassapplication.FadeIconsListener;
import com.englishtopass.englishtopassapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class MainSettingFloatingActionButton extends FloatingActionButton {

    private static final String TAG = "MainSettingFloatingActi";

    private FadeIconsListener fadeIconsListener;

    ConstraintLayout constraintLayout;
    ConstraintSet rootConstraintSet;
    ConstraintSet showSettingsConstraintSet;
    CompositeDisposable compositeDisposable;

    public MainSettingFloatingActionButton(Context context) {
        super(context);
        init();
    }

    public MainSettingFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainSettingFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        compositeDisposable = new CompositeDisposable();

        rootConstraintSet = new ConstraintSet();

        showSettingsConstraintSet = new ConstraintSet();

    }

    public void setFadeIconsListener(FadeIconsListener fadeIconsListener) {
        this.fadeIconsListener = fadeIconsListener;
    }

    public void setUpConstraintLayout(ConstraintLayout constraintLayout) {

        this.constraintLayout = constraintLayout;

        setUpConstraintSets();

    }



    private void setUpConstraintSets() {

        returnSetCompletable()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onComplete() {

                        setClickable(true);

                        compositeDisposable.dispose();

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d(TAG, "onError:" + e.getLocalizedMessage());

                    }
                });
    }

    public Completable returnSetCompletable(){

        return Completable.fromRunnable(
                this::createConstraintSets
        );

    }

    public void createConstraintSets(){

        rootConstraintSet.clone(constraintLayout);

        showSettingsConstraintSet.clone(getContext(), R.layout.fragment_multiple_choice_cloze_question_set);

    }

    public boolean showSettingsButtons(boolean set){

        if (set) {

            fadeIconsListener.fadeOutIcons();

        }

        boolean finalSet = set;
        TransitionSet transitionSet = new TransitionSet()
                .addTransition(new ChangeBounds())
                .addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                        Log.d(TAG, "onTransitionStart: start");
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {

                        if (!finalSet){

                            fadeIconsListener.fadeInIcons();

                        }

                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                        Log.d(TAG, "onTransitionCancel: hello");

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {



                    }

                    @Override
                    public void onTransitionResume(Transition transition) {
                    }
                });


        TransitionManager.beginDelayedTransition(constraintLayout, transitionSet);

        ConstraintSet cSet = (set) ? rootConstraintSet : showSettingsConstraintSet ;

        cSet.applyTo(constraintLayout);

        set = !set;

        return set;

    }

    public void changeButtonIcon(int fabSetting) {

        this.animate()
                .rotation(getRotation() + 360f)
                .setDuration(100)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        setImageResource(fabSetting);

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }

                }).start();

    }


}

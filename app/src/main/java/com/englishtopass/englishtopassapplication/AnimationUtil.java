//package com.englishtopass.englishtopassapplication;
//
//import android.animation.Animator;
//import android.animation.AnimatorSet;
//import android.animation.ObjectAnimator;
//import android.animation.PropertyValuesHolder;
//
//import com.englishtopass.englishtopassapplication.CustomViews.MainSettingFloatingActionButton;
//import com.englishtopass.englishtopassapplication.CustomViews.SettingsFloatingActionButton;
//
//public class AnimationUtil {
//
//    public AnimatorSet showSettingsIcons(SettingsFloatingActionButton f1, SettingsFloatingActionButton f2,
//                                         SettingsFloatingActionButton f3, SettingsFloatingActionButton f4,
//                                         MainSettingFloatingActionButton mainSettingFloatingActionButton){
//
//        AnimatorSet animatorSet = returnAnimatorSet();
//
//        animatorSet.playSequentially(
//
//                ObjectAnimator.ofPropertyValuesHolder(
//                        f1.getDrawable(), PropertyValuesHolder.ofInt("alpha", 0, 255)),
//
//                ObjectAnimator.ofPropertyValuesHolder(
//                        f2.getDrawable(), PropertyValuesHolder.ofInt("alpha", 0, 255)),
//
//                ObjectAnimator.ofPropertyValuesHolder(
//                        f3.getDrawable(), PropertyValuesHolder.ofInt("alpha", 0, 255)),
//
//                ObjectAnimator.ofPropertyValuesHolder(
//                        f4.getDrawable(), PropertyValuesHolder.ofInt("alpha", 0, 255))
//
//        );
//
//        animatorSet.setDuration(100).addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mainSettingFloatingActionButton.setClickable(true);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//
//        return animatorSet;
//
//    }
//
//    public AnimatorSet hideSettingsIcon(SettingsFloatingActionButton f1,SettingsFloatingActionButton f2,
//                                        SettingsFloatingActionButton f3,SettingsFloatingActionButton f4,
//                                        MainSettingFloatingActionButton mainSettingFloatingActionButton){
//
//        AnimatorSet animatorSet = returnAnimatorSet();
//
//        animatorSet.playTogether(
//
//                ObjectAnimator.ofPropertyValuesHolder(
//                        f1.getDrawable(), PropertyValuesHolder.ofInt("alpha", 255, 0)),
//
//                ObjectAnimator.ofPropertyValuesHolder(
//                        f2.getDrawable(), PropertyValuesHolder.ofInt("alpha", 255, 0)),
//
//                ObjectAnimator.ofPropertyValuesHolder(
//                        f3.getDrawable(), PropertyValuesHolder.ofInt("alpha", 255, 0)),
//
//                ObjectAnimator.ofPropertyValuesHolder(
//                        f4.getDrawable(), PropertyValuesHolder.ofInt("alpha", 255, 0))
//
//        );
//
//        animatorSet.setDuration(80)
//                .addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        mainSettingFloatingActionButton.setClickable(true);
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
//
//        return animatorSet;
//
//    }
//
//    private AnimatorSet returnAnimatorSet(){
//
//        return new AnimatorSet();
//
//    }
//
//
//
//}

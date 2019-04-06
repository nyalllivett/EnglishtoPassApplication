package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.animation.Animator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.CustomViews.MovableFloatingActionButton;
import com.englishtopass.englishtopassapplication.CustomViews.TaggableClickableSpan;
import com.englishtopass.englishtopassapplication.Enums.FabSetting;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.ViewModels.UoeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultipleChoiceClozeQuestion extends Fragment implements View.OnClickListener {
    private static final String TAG = "MultipleChoiceClozeQues";
    private int packageId;
    private Pattern pattern;
    private SpannableStringBuilder spannableStringBuilder;
    private CompositeDisposable compositeDisposable;
    private Chronometer chronometer;
    private TextView questionBody;
    private FloatingActionButton floatingActionButton;
    private int spanSelected;
    private FabSetting fabSetting = FabSetting.SETTINGS;
    private MovableFloatingActionButton timerFab, fontSizeFab, viewPageFab, hideSettingsFab;
    private ConstraintLayout questionRoot;
    private ConstraintSet constraintSet2, constraintSet;
    private FrameLayout frameLayout;
    private boolean progressHolderCreated;
    private boolean set = false;

    public MultipleChoiceClozeQuestion() {
        // Required empty public constructor
    }


    public static MultipleChoiceClozeQuestion newInstance(int packageId) {
        MultipleChoiceClozeQuestion fragment = new MultipleChoiceClozeQuestion();
        Bundle args = new Bundle();
        args.putInt("packageId", packageId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            packageId = getArguments().getInt("packageId");

        }

        compositeDisposable = new CompositeDisposable();

        pattern = Pattern.compile("[.]{8}");

        setSpanSelected(-1);

        progressHolderCreated = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multiple_choice_cloze_question, container, false);

        TextView questionTitle = view.findViewById(R.id.questionTitle);

        questionRoot = view.findViewById(R.id.questionRoot);

        questionBody = view.findViewById(R.id.questionBody);

        questionBody.setOnClickListener(this);

        frameLayout = view.findViewById(R.id.settingsHolder);

        questionBody.setMovementMethod(LinkMovementMethod.getInstance());

        floatingActionButton = view.findViewById(R.id.showSettingsFab);

        floatingActionButton.setOnClickListener(this);

        fontSizeFab = view.findViewById(R.id.viewPageFab);

        fontSizeFab.setOnClickListener(this);

        chronometer = view.findViewById(R.id.timer);

        pattern = Pattern.compile("[.]{8}");

        UoeViewModel viewModel = ViewModelProviders.of(this).get(UoeViewModel.class);

        viewModel.getMenuMultipleChoiceQuestion(packageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onSuccess(com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion multipleChoiceClozeQuestion) {

                        questionTitle.setText(multipleChoiceClozeQuestion.getTitle());

                        String retrievedBody = multipleChoiceClozeQuestion.getBody();

                        spannableStringBuilder = new SpannableStringBuilder(retrievedBody);

                        findPattern(retrievedBody);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d(TAG, "onError: " + e.getLocalizedMessage());

                    }

                });

        set = false;
        constraintSet = new ConstraintSet();
        constraintSet2 = new ConstraintSet();

        constraintSet.clone(questionRoot);

        constraintSet2.clone(getContext(), R.layout.fragment_multiple_choice_cloze_question_set);


        return view;

    }

    private void findPattern(String body){

        Matcher matcher = pattern.matcher(body);

        int tagCounter = 0;

        while (matcher.find()) {

            createClickableSpan(matcher.start(), matcher.end(), tagCounter);

            Log.d(TAG, "onSuccess: " + matcher.start() + " " + matcher.end());

            tagCounter++;

        }

        setClickableText();

    }



    private void createClickableSpan(int startingIndex, int endingIndex, int tag){

        spannableStringBuilder.setSpan(new TaggableClickableSpan(tag) {
            @Override
            public void onClick(@NonNull View widget) {

                if (getSpanSelected() < 0) {

                    changeFabIcon(toggleSettings());

                    setSpanSelected(this.getTag());

                    Log.d(TAG, "onClick: span tag" + getSpanSelected());

                }



            }

        }, startingIndex, endingIndex, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

    }

    private int toggleSettings() {

        if (fabSetting == FabSetting.SETTINGS) {

            fabSetting = FabSetting.ANSWER;

            return R.drawable.answer_24dp;

        } else {

            fabSetting = FabSetting.SETTINGS;

            return R.drawable.tool_box_24dp;

        }

    }

    private void setClickableText(){

        questionBody.setText(spannableStringBuilder);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.questionBody:

                if (questionBody.getSelectionStart() == -1 && questionBody.getSelectionEnd() == -1 && fabSetting == FabSetting.ANSWER) {

                    changeFabIcon(toggleSettings());

                    setSpanSelected(-1);

                    Log.d(TAG, "onClick: span tag" + getSpanSelected());

                }

                break;

            case R.id.showSettingsFab:

                TransitionManager.beginDelayedTransition(questionRoot);

                ConstraintSet cSet = (set) ? constraintSet : constraintSet2;

                cSet.applyTo(questionRoot);

                set = !set;

                break;

            case R.id.viewPageFab:

                if (!progressHolderCreated) {

                    createProgressbarHolder();

                } else {

                    removeProgressbarHolder();

                }

                break;
        }

    }

    private void removeProgressbarHolder() {

        frameLayout.removeAllViews();

        progressHolderCreated = false;

    }

    private void createProgressbarHolder() {

        RelativeLayout relativeLayout = new RelativeLayout(getContext());

        relativeLayout.setBackgroundResource(R.drawable.pop_up_grey_box);

        frameLayout.addView(relativeLayout);

        FrameLayout.LayoutParams relativeParams = (FrameLayout.LayoutParams) relativeLayout.getLayoutParams();

        relativeParams.height = FrameLayout.LayoutParams.MATCH_PARENT;
        relativeParams.width = FrameLayout.LayoutParams.MATCH_PARENT;

        relativeLayout.setLayoutParams(relativeParams);

        SeekBar progressBar = new SeekBar(getContext());

        progressBar.setBackgroundResource(R.drawable.custom_seek_bar);

        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) progressBar.getLayoutParams();

        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        params.width = 500;
        params.height = 100;

        progressBar.setLayoutParams(params);

        progressHolderCreated = true;
    }

    private void changeFabIcon(int fabSetting){


        floatingActionButton.animate()
                .rotation(360)
                .setDuration(100)
                .setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                floatingActionButton.setImageResource(fabSetting);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                floatingActionButton.setRotation(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        }).start();

    }

    public int getSpanSelected() {
        return spanSelected;
    }

    public void setSpanSelected(int spanSelected) {
        this.spanSelected = spanSelected;
    }



    private void startTimer() {

        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.start();

    }

    private void endTimer() {

        chronometer.stop();

    }

    @Override
    public void onDestroy() {

        compositeDisposable.dispose();

        super.onDestroy();

    }
}

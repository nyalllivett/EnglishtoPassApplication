package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.AnimationUtil;
import com.englishtopass.englishtopassapplication.CustomViews.MainSettingFloatingActionButton;
import com.englishtopass.englishtopassapplication.CustomViews.SettingsFloatingActionButton;
import com.englishtopass.englishtopassapplication.CustomViews.TaggableClickableSpan;
import com.englishtopass.englishtopassapplication.Enums.FabSetting;
import com.englishtopass.englishtopassapplication.FadeIconsListener;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.ViewModels.UoeViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultipleChoiceClozeQuestion extends Fragment implements View.OnClickListener, FadeIconsListener {
    private static final String TAG = "MultipleChoiceClozeQues";
    private int packageId;
    private Pattern pattern;
    private SpannableStringBuilder spannableStringBuilder;
    private CompositeDisposable compositeDisposable;
    private Chronometer chronometer;
    private TextView questionBody;
    private int spanSelected;
    private FabSetting fabSetting = FabSetting.SETTINGS;
    private SettingsFloatingActionButton timerFab, fontSizeFab, viewPageFab, hideSettingsFab;
    private MainSettingFloatingActionButton floatingActionButton;
    private ConstraintLayout questionRoot;
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

        // Constraint layout/set

        questionRoot = view.findViewById(R.id.questionRoot);

        questionBody = view.findViewById(R.id.questionBody);

        questionBody.setOnClickListener(this);

        questionBody.setMovementMethod(LinkMovementMethod.getInstance());

        // Settings menu

        // Main settings button
        floatingActionButton = view.findViewById(R.id.showSettingsFab);

        floatingActionButton.setUpConstraintLayout(questionRoot);

        floatingActionButton.setOnClickListener(this);

        floatingActionButton.setFadeIconsListener(this);

        // Font size Fab
        fontSizeFab = view.findViewById(R.id.fontSizeFab);

        fontSizeFab.setOnClickListener(this);

        // Timer Fab
        timerFab = view.findViewById(R.id.viewTimerFab);

        timerFab.setOnClickListener(this);

        // View page Fab
        viewPageFab = view.findViewById(R.id.viewPageFab);

        viewPageFab.setOnClickListener(this);

        // Hide setting Fab
        hideSettingsFab = view.findViewById(R.id.hideSettingsFab);

        hideSettingsFab.setOnClickListener(this);

        // Timer

        chronometer = view.findViewById(R.id.timer);

        // Regex

        pattern = Pattern.compile("[.]{8}");

        // Frame for font size seek bar

        frameLayout = view.findViewById(R.id.settingsHolder);

        // View model

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

                    floatingActionButton.changeButtonIcon(toggleSettings());

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

                    floatingActionButton.changeButtonIcon(toggleSettings());

                    setSpanSelected(-1);

                }

                break;

            case R.id.showSettingsFab:

                set = floatingActionButton.showSettingsButtons(set);

                floatingActionButton.setClickable(false);

                showIcons(set);

                break;

            case R.id.fontSizeFab:

                if (!progressHolderCreated) {

                    createProgressbarHolder();

                } else {

                    removeProgressbarHolder();

                }

                break;
        }

    }

    private void showIcons(boolean set) {

        if (set) {

            fontSizeFab.setImageResource(R.drawable.answer_24dp);

            fontSizeFab.getDrawable().setAlpha(0);

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

    @Override
    public void fadeInIcons() {

        new AnimationUtil().showSettingsIcons(hideSettingsFab, viewPageFab, fontSizeFab, timerFab, floatingActionButton).start();

    }

    @Override
    public void fadeOutIcons() {

        new AnimationUtil().hideSettingsIcon(hideSettingsFab, viewPageFab, fontSizeFab, timerFab, floatingActionButton).start();

    }
}

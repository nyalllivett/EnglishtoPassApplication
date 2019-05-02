package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.CustomViews.ClickableSpanCustom;
import com.englishtopass.englishtopassapplication.CustomViews.LinkMovementMethodCustom;
import com.englishtopass.englishtopassapplication.Interfaces.SetScrollYListener;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.UtilClass;
import com.englishtopass.englishtopassapplication.ViewModels.UoeViewModel;

import java.util.regex.Matcher;


public class MultipleChoiceClozeQuestion extends MultipleChoiceQuestion implements View.OnClickListener, SetScrollYListener {
    private static final String TAG = "MultipleChoiceClozeQues";

    /** PACKAGE ID
     * this is used to identify which package the question should be
     * taken from. Used in retrieving package vid Rxjava
     */
    private int packageId;

    /** VIEW MODEL
     * used to store and retrieve data and survive configuration changes.
     */
    private UoeViewModel viewModel;

    /** CURRENT QUESTION OBJECT
     * current object to work with, we save it in memory so we can update
     * the scores and time then save it back in the database
     */
    private com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion multipleChoiceCloze;

    public MultipleChoiceClozeQuestion() {
        // Required empty public constructor
    }

    /**
     * creating a new instance, called from parent fragment, will have
     * packageId as a param so we know which package is current
     * @param packageId
     * current package in scope
     * @return
     * returns fragment to fragment manager in the parent fragment
     */
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

        sqCompileRegexPattern("[.]{8}");

        sqSetSpanSelected(-1);

    }

    /**
     * where the main bulk of the work is done.
     * @param inflater
     * /
     * @param container
     * /
     * @param savedInstanceState
     * /
     * @return
     * /
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // INFLATE THE CORRECT VIEW FOR THE QUESTION
        View view = inflater.inflate(R.layout.fragment_multiple_choice_cloze_question, container, false);

        // INITIALISE THE TITLE TEXT VIEW FROM THE LAYOUT
        TextView questionTitle = view.findViewById(R.id.questionTitle);

        // INITIALISE THE QUESTION BODY TEXT VIEW FROM THE LAYOUT
        questionBody = view.findViewById(R.id.questionBody);

        // SET ON CLICK LISTENING FOR THE QUESTION BODY
        questionBody.setOnClickListener(this);

        // SET ON CLICK LISTENER FOR THE FINISH BUTTON - UNFINISHED
        view.findViewById(R.id.finishTestButton).setOnClickListener(this);
        view.findViewById(R.id.showTimer).setOnClickListener(this);

        // CREATE LINK MOVEMENT METHOD INSTANCE
        LinkMovementMethodCustom linkMovementMethodCustom = LinkMovementMethodCustom.getInstance();

        // SET THE SCROLL LISTENER FOR THE LLM
        linkMovementMethodCustom.setSetScrollYListener(this);

        // SET THE QUESTION BODY LLM TO THIS
        questionBody.setMovementMethod(linkMovementMethodCustom);

        // INITIALISE THE SCROLL VIEW
        scrollView = view.findViewById(R.id.scrollView);

        // INITIALISE THE CHRONOMETER
        chronometer = view.findViewById(R.id.timer);

        // INITIALISE THE FRAME LAYOUT
        actionFrameLayout = view.findViewById(R.id.actionFrameLayout);

        // ACCESS CURRENT QUESTION DATABASE
        viewModel = ViewModelProviders.of(this).get(UoeViewModel.class);

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

                        multipleChoiceCloze = multipleChoiceClozeQuestion;

                        int partitionNumber = 4;

                        mcaPartition(UtilClass.stringSplitter(multipleChoiceClozeQuestion.getAllAnswers()), partitionNumber);

                        qSetCorrectAnswers(UtilClass.stringSplitter(multipleChoiceClozeQuestion.getCorrectAnswers()));

                        questionTitle.setText(multipleChoiceClozeQuestion.getTitle());

                        String retrievedBody = multipleChoiceClozeQuestion.getBody();

                        spannableStringBuilder = new SpannableStringBuilder(retrievedBody);

                        findPattern(retrievedBody);

                        if (!viewModel.isQuestionStarted()){

                            chronometer.setBaseTime(viewModel.getSavedTimeElapsed() + multipleChoiceCloze.getTestTimeElapsed());

                            chronometer.start();

                            viewModel.setQuestionStarted(true);

                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d(TAG, "onError: " + e.getLocalizedMessage());

                    }

                });

        // RETURN VIEW
        return view;

    }


    /**
     * handling the hiding of the action frame if its open, will return
     * early if so, then will also handle any buttons at high level like
     * view timer or finish test
     * @param v
     * v thats in scope
     */
    @Override
    public void onClick(View v) {
        if (sqIsActionFrameOpen() && bufferTimeElapsed) {
            sqHideActionFrame();
            return;
        }
        switch (v.getId()) {
            case R.id.finishTestButton:
                finishTest();
                break;
            case R.id.showTimer:
                chronometer.showTime();
                break;
        }
    }

    /**
     * method called by findPattern method to create the spans. also set the
     * onClick method at the same time.
     * @param startingIndex
     * @param endingIndex
     * @param tag
     */
    private void createClickableSpan(int startingIndex, int endingIndex, int tag){
        spannableStringBuilder.setSpan(new ClickableSpanCustom(tag) {
            @Override
            public void onClick(@NonNull View widget) {
                sqSetSpanSelected(this.getTag());
                TextView textView = (TextView) widget;
                Spannable spannable = (Spannable) textView.getText();
                sqSetCurrentSpanIndices(spannable.getSpanStart(this), spannable.getSpanEnd(this));
                if (!sqIsActionFrameOpen()) {
                    sqOpenActionFrame();
                    mcaInflateMultipleChoiceView();
                }
            }
        }, startingIndex, endingIndex, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
    }


    /**
     * finds the blanks in the searchable string provided and when found will
     * a clickable span in the corresponding position in the spannable
     * string builder
     * @param body
     */
    protected void findPattern(String body){
        Matcher matcher = pattern.matcher(body);
        int tagCounter = 0;
        while (matcher.find()) {
            createClickableSpan(matcher.start(), matcher.end(), tagCounter);
            tagCounter++;
        }
        questionBody.setText(spannableStringBuilder);
    }


    public void finishTest(){

//        multipleChoiceCloze.finishQuestion(chronometer.getFinishedElapsedTime(), true, sqGetCheckedAnswerBooleans());

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                viewModel.updateMultipleChoice(multipleChoiceCloze);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: hello");
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }
                });
    }

    /**
     * this is called when the fragment is created and when a state changes happens,
     * so on start multipleChoiceCloze hasn't been initialized yet. so we check if its
     * null, if not that means the state has been changed and the time should have been saved
     * to the view model.
     * If yes we start the timer from the view model saved time.
     */
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        if (viewModel.isQuestionStarted()){
            chronometer.setBaseTime(viewModel.getSavedTimeElapsed());
            startTimer();
        }
            super.onResume();
    }

    /**
     * saving the time already elapsed in view model as there is a state change
     * coming up. this is called on rotation and when interrupted.
     * the time saved is from the elapsed time which is got from
     * System.timeElapsed - getBase()
     * getBase() is set from the time from the database originally.
     */
    @Override
    public void onPause() {
        viewModel.setSavedTimeElapsed(chronometer.returnElapsedTime());
        super.onPause();
    }

    // TODO: 02/05/2019 must end timer to stop memory leak
    @Override
    public void onDestroy() {
        endTimer();
        super.onDestroy();

    }

}

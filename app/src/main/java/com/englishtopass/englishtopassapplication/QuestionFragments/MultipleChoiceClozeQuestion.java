package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import android.os.Handler;
import android.os.SystemClock;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.CustomViews.ChronometerCustom;
import com.englishtopass.englishtopassapplication.CustomViews.LinkMovementMethodCustom;
import com.englishtopass.englishtopassapplication.CustomViews.ClickableSpanCustom;
import com.englishtopass.englishtopassapplication.Interfaces.SetScrollYListener;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.UtilClass;
import com.englishtopass.englishtopassapplication.ViewModels.UoeViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MultipleChoiceClozeQuestion extends MultipleChoiceAnswer implements View.OnClickListener, SetScrollYListener {
    private static final String TAG = "MultipleChoiceClozeQues";

    // identify package
    private int packageId;

    // ViewModel
    private UoeViewModel viewModel;

    //
    private com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion multipleChoiceCloze;

    // regex for identifying the blank spaces
//    private Pattern pattern;

    // builder to build spans
//    private SpannableStringBuilder spannableStringBuilder;

    // Dispose of unused rx's
    private CompositeDisposable compositeDisposable;

    // Timer
    private ChronometerCustom chronometer;

    // Views
//    private TextView questionBody;
    private FrameLayout actionFrameLayout;
    private ScrollView scrollView;

    // identifier for which span is selected
//    private int spanSelected;

    // Touch coords
    private float actionFrameY;
    private int spanYPosition;
    private float scrollAmount;

    // boolean for touch coords
    private boolean actionBarOpen;
    private boolean bufferTimeElapsed;

    // arrays to store answers in memory
//    private String[][] partitionedAnswers = new String[8][4]; // MOVED TO MULTIPLE CHOICE ANSWER PARENT
    private String[] correctAnswers;
//    private int[] spanIndices = new int[2];

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

        sqCompileRegexPattern("[.]{8}");

        sqSetSpanSelected(-1);

        actionBarOpen = false;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multiple_choice_cloze_question, container, false);

        TextView questionTitle = view.findViewById(R.id.questionTitle);

        // Constraint layout/set

        questionBody = view.findViewById(R.id.questionBody);

        questionBody.setOnClickListener(this);

        view.findViewById(R.id.finishTestButton).setOnClickListener(this);

        LinkMovementMethodCustom linkMovementMethodCustom = LinkMovementMethodCustom.getInstance();

        linkMovementMethodCustom.setSetScrollYListener(this);

        questionBody.setMovementMethod(linkMovementMethodCustom);

        scrollView = view.findViewById(R.id.scrollView);

        // Timer

        chronometer = view.findViewById(R.id.timer);

        startTimer();

        // Regex

//        pattern = Pattern.compile("[.]{8}");

        // Frame for actions

        actionFrameLayout = view.findViewById(R.id.actionFrameLayout);

        // View model

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

                        /*
                            mcaPartition is parent method called from MultipleChoiceAnswer to
                            mcaPartition the answers in the groups of four in this case.
                            params:
                            2D array of all answers, size of 3D array/mcaPartition
                         */
                        mcaPartition(UtilClass.stringSplitter(multipleChoiceClozeQuestion.getAllAnswers()), 4);

                        /*
                         */
                        setCorrectAnswers(UtilClass.stringSplitter(multipleChoiceClozeQuestion.getCorrectAnswers()));

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

            tagCounter++;

        }

        questionBody.setText(spannableStringBuilder);

    }

    private void createClickableSpan(int startingIndex, int endingIndex, int tag){

        spannableStringBuilder.setSpan(new ClickableSpanCustom(tag) {

            @Override
            public void onClick(@NonNull View widget) {

                sqSetSpanSelected(this.getTag());

                TextView textView = (TextView) widget;

                Spannable spannable = (Spannable) textView.getText();

                sqSetCurrentSpanIndices(spannable.getSpanStart(this), spannable.getSpanEnd(this));

                if (!actionBarOpen)
                    openActionBar();
            }

        }, startingIndex, endingIndex, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

    }



    private void openActionBar(){

        actionFrameLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {

                        actionFrameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        // This is open,
                        actionBarOpen = true;

                        bufferTimeElapsed = false;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                               bufferTimeElapsed = true;
                            }
                        }, 100);

                        actionFrameY = actionFrameLayout.getY();

                        scrollQuestionText(null);

                    }

                }
        );

        View.OnClickListener onClickListenerAnswer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.multipleChoiceOne:
                    case R.id.multipleChoiceTwo:
                    case R.id.multipleChoiceThree:
                    case R.id.multipleChoiceFour:

                        if (sqGetCurrentSpanIndices()[0] != -1 && sqGetCurrentSpanIndices()[1] != -1) {

                            sqReplaceAndSetBuilderText(((Button) v).getText().toString());

                            for (ClickableSpanCustom clickableSpanCustom : sqReturnOrderedSpanList()) {

                                if (clickableSpanCustom.getTag() == sqGetSpanSelected()) {

                                    clickableSpanCustom.setAnswerTag(((Button) v).getText().toString());

                                    hideActionFrame();

                                    return;

                                }

                            }

                            return;

                        } else {

                            throw new IllegalStateException("Span indices incorrect");

                        }

                    case R.id.resetSpanText:

                        sqReplaceAndSetBuilderText("........");

                        break;

                }

            }
        };

        View view = LayoutInflater.from(getContext()).inflate(R.layout.multiple_choice_questions, actionFrameLayout, true);

        view.findViewById(R.id.hideActionFrameNumber).setOnClickListener(this);

        view.findViewById(R.id.resetSpanText).setOnClickListener(onClickListenerAnswer);

        LinearLayout linearLayout = view.findViewById(R.id.linearQuestions);


        for (int i = 0; i < linearLayout.getChildCount(); i++) {

            Button button = (Button) linearLayout.getChildAt(i);

            button.setText(partitionedAnswers[sqGetSpanSelected()][i]);

            button.setOnClickListener(onClickListenerAnswer);

        }

    }



    @Override
    public void onClick(View v) {

        if (actionBarOpen && bufferTimeElapsed) {

            hideActionFrame();

            return;

        }

        switch (v.getId()) {

//            case R.id.questionBody:
//
////                if (sqGetSpanSelected() >= 0 && questionBody.getSelectionStart() == -1 && questionBody.getSelectionEnd() == -1) {
////
////                    Log.d(TAG, "onClick: " + questionBody.getSelectionStart());
////                    sqSetSpanSelected(-1);
////                    sqSetCurrentSpanIndices(-1, -1);
////
////                }
//
//                break;

            case R.id.hideActionFrameNumber:

                hideActionFrame();

                break;

            case R.id.finishTestButton:

                finishTest();

                break;

        }



    }

    // SPAN METHODS
    // Called from CustomLinkMovementMethod -
    @Override
    public void setSpanYPosition(int yCoord) {
        this.spanYPosition = yCoord;
    }

    // Called from scrollQuestionText()
    private int getSpanYPosition() {
        return spanYPosition;
    }

    // Called from openActionBar() & hideActionBar()
    private void scrollQuestionText(@Nullable Integer scrollAmountBack){

        if (null != scrollAmountBack) {

            scrollView.scrollTo(0, -scrollAmountBack);

            return;

        }

        scrollAmount = (getSpanYPosition() - actionFrameY);

        if (scrollAmount > 0) {

            scrollView.scrollTo(0, (int) (scrollAmount) + 10);

        }

    }



    public String[] getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    // Called from sqSetClickableText() & main onClick
//    private List<ClickableSpanCustom> sqReturnOrderedSpanList() {
//
//        List<ClickableSpanCustom> clickableSpans = Arrays.asList(spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), ClickableSpanCustom.class));
//
//        Collections.sort(clickableSpans, new SpanByTagComparator());
//
//        return clickableSpans;
//
//    }

    // QUESTION METHODS
    // CHRONO METHODS
    private void startTimer() {

        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.start();

    }

    // QUESTION METHOD
    private void endTimer() {

        chronometer.stop();

    }

    // QUESTION METHOD
    // hide action frame view
    private void hideActionFrame() {

        actionFrameLayout.removeAllViews();

        actionBarOpen = false;

        scrollQuestionText((int) scrollAmount);

        sqSetSpanSelected(-1);

        sqSetCurrentSpanIndices(-1, -1);

    }

    // QUESTION METHOD for any question where we compare answers.
    // Creating the set of answers



    public void finishTest(){

        multipleChoiceCloze.setAnswersAreCorrect(getCheckedAnswerBooleans());
        multipleChoiceCloze.setComplete(true);
        multipleChoiceCloze.setTestTimeElapsed(836736);

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

    private boolean[] getCheckedAnswerBooleans() {

        int i = 0;

        boolean[] answers = new boolean[8];

        for (ClickableSpanCustom span : sqReturnOrderedSpanList()) {

            answers[i] = span.getAnswerTag().equals(getCorrectAnswers()[i]);

            i++;

        }

        return answers;

    }

    @Override
    public void onDestroy() {

        endTimer();

        compositeDisposable.dispose();

        super.onDestroy();

    }


}

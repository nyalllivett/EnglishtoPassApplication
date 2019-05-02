package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.os.Bundle;
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
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.UtilClass;
import com.englishtopass.englishtopassapplication.ViewModels.UoeViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class KeywordTransformationQuestion extends WrittenAnswer implements View.OnClickListener {
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
    private com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion openClozeQuestion;

    public KeywordTransformationQuestion() {
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
    public static KeywordTransformationQuestion newInstance(int packageId) {
        KeywordTransformationQuestion fragment = new KeywordTransformationQuestion();
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

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

        // START THE TIMER BY METHOD START TIMER
//        startTimer();

        // INITIALISE THE FRAME LAYOUT
        actionFrameLayout = view.findViewById(R.id.actionFrameLayout);

        // ACCESS CURRENT QUESTION DATABASE
        viewModel = ViewModelProviders.of(this).get(UoeViewModel.class);

//        viewModel.getOpenClozeQuestionLiveData(packageId).observe(getViewLifecycleOwner(), new Observer<com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion>() {
//            @Override
//            public void onChanged(com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion openCloze) {
//
//                openClozeQuestion = openCloze;
//
//                qSetCorrectAnswers(UtilClass.stringSplitter(openCloze.getCorrectAnswers()));
//
//                questionTitle.setText(openCloze.getTitle());
//
//                String retrievedBody = openCloze.getBody();
//
//                spannableStringBuilder = new SpannableStringBuilder(retrievedBody);
//
//                findPattern(retrievedBody);
//
//            }
//        });

        return view;
    }
    /**
     * method called by findPattern method to create the spans. also set the
     * onClick method at the same time.
     * @param startingIndex
     * index on where to start the clickable span
     * @param endingIndex
     * index on where to start the clickable span
     * @param tag
     * the tag number set on the span
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
                    waInflateWrittenAnswerView();
                }
            }
        }, startingIndex, endingIndex, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
    }

    /**
     * finds the blanks in the searchable string provided and when found will
     * a clickable span in the corresponding position in the spannable
     * string builder
     * @param body
     * the string that is being searched
     */
    private void findPattern(String body){
        Matcher matcher = pattern.matcher(body);
        int tagCounter = 0;
        while (matcher.find()) {
            createClickableSpan(matcher.start(), matcher.end(), tagCounter);
            tagCounter++;
        }
        questionBody.setText(spannableStringBuilder);
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
        }
    }

    public void finishTest(){
//
//        openClozeQuestion.setAnswersAreCorrect(sqGetCheckedAnswerBooleans());
//        openClozeQuestion.setComplete(true);
//        openClozeQuestion.setTestTimeElapsed(836736);
//
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                viewModel.updateMultipleChoice(openClozeQuestion);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: hello");
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
//                    }
//                });
//
//

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}

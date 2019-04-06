package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.ViewModels.UoeViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WordFormationQuestion extends Fragment {
    private static final String TAG = "MultipleChoiceClozeQues";
    private int packageId;
    private Pattern pattern;
    private SpannableStringBuilder spannableStringBuilder;
    private CompositeDisposable compositeDisposable;

    public WordFormationQuestion() {
        // Required empty public constructor
    }


    public static WordFormationQuestion newInstance(int packageId) {
        WordFormationQuestion fragment = new WordFormationQuestion();
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

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multiple_choice_cloze_question, container, false);

        TextView questionBody = view.findViewById(R.id.questionBody);

        TextView questionTitle = view.findViewById(R.id.questionTitle);

        pattern = Pattern.compile("[.]{8}");

        UoeViewModel viewModel = ViewModelProviders.of(this).get(UoeViewModel.class);

        viewModel.getMenuOpenClozeQuestion(packageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onSuccess(com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion openClozeQuestion) {

                        questionTitle.setText(openClozeQuestion.getTitle());

                        spannableStringBuilder = new SpannableStringBuilder(openClozeQuestion.getBody());

                        questionBody.setText(spannableStringBuilder);

                        Matcher matcher = pattern.matcher(openClozeQuestion.getBody());

                        while (matcher.find()) {

                            Log.d(TAG, "onSuccess: " + matcher.start() + " " + matcher.end());

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroy() {

        compositeDisposable.dispose();

        super.onDestroy();
    }
}

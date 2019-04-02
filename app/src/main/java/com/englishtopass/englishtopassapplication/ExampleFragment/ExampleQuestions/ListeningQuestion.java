package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions;


import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.CustomViews.MovableFloatingActionButton;
import com.englishtopass.englishtopassapplication.Enums.TestCompletion;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.GrandParent.BaseQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.R;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;

public class ListeningQuestion extends BaseQuestion implements OnBackPressedCallback, View.OnClickListener {
    private static final String TAG = "UoeExampleQuestion";

    private TestCompletion testCompletion;

    private MultipleChoiceClozeQuestion multipleChoiceClozeQuestion;
    private MovableFloatingActionButton movableFloatingActionButton;

    public ListeningQuestion() {
        // Required empty public constructor
    }

    public static ListeningQuestion newInstance(TestCompletion testCompletion) {

        Bundle bundle = new Bundle();

        bundle.putSerializable("TEST_COMPLETION", testCompletion);

        ListeningQuestion uoeExampleQuestion = new ListeningQuestion();

        uoeExampleQuestion.setArguments(bundle);

        return uoeExampleQuestion;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            testCompletion = TestCompletion.values()[getArguments().getInt("TEST_COMPLETION")];

        }



        ArrayList<String> exampleGroup = new ArrayList<>(Arrays.asList(String.valueOf(R.string.multiple_choice_cloze_example_answer_group).trim().split("#")));

        multipleChoiceClozeQuestion =
                new MultipleChoiceClozeQuestion(
                        String.valueOf(getString(R.string.multiple_choice_cloze_example_title)),
                        String.valueOf(getString(R.string.multiple_choice_cloze_example_body)),
                        exampleGroup,
                        String.valueOf(getString(R.string.multiple_choice_cloze_example_answer))
                );

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        getActivity().addOnBackPressedCallback(getViewLifecycleOwner(), this);

        startTimer();

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_uoe_example_question, container, false);

        TextView exampleBody = view.findViewById(R.id.uoe_example_body);

        exampleBody.setText(multipleChoiceClozeQuestion.getBody());

        TextView exampleTitle = view.findViewById(R.id.uoe_example_title);

        exampleTitle.setText(multipleChoiceClozeQuestion.getTitle());

        chronometer = view.findViewById(R.id.chronometer);

        // Setting the FAB

        movableFloatingActionButton = view.findViewById(R.id.movableFab);

        movableFloatingActionButton.setOnClickListener(this);


        // Setting the spans

        exampleBody.setText(searchAndSetSpans(multipleChoiceClozeQuestion.getBody()));

        exampleBody.setMovementMethod(LinkMovementMethod.getInstance());

        return view;

    }


    @Override
    public void onClick(View v) {

        super.onClick(v);

    }

    @Override
    public boolean handleOnBackPressed() {

        return false;

    }

    @Override
    public void onDestroy() {

        endTimer();

        getActivity().removeOnBackPressedCallback(this);

        super.onDestroy();
    }


}

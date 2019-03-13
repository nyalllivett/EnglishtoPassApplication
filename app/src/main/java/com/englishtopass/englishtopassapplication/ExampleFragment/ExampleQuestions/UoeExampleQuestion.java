package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UoeExampleQuestion extends Fragment implements OnBackPressedCallback {
    private static final String TAG = "UoeExampleQuestion";

    private MultipleChoiceClozeQuestion multipleChoiceClozeQuestion;


    public UoeExampleQuestion() {
        // Required empty public constructor
    }

    public static UoeExampleQuestion newInstance() {
        return new UoeExampleQuestion();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: hello");

        getActivity().addOnBackPressedCallback(getViewLifecycleOwner(), this);

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {

        Log.d(TAG, "onDestroy: destroyed callback");
        getActivity().removeOnBackPressedCallback(this);

        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> exampleGroup = new ArrayList<>(Arrays.asList(String.valueOf(R.string.multiple_choice_cloze_example_answer_group).trim().split("#")));


        multipleChoiceClozeQuestion =
                new MultipleChoiceClozeQuestion(
                        String.valueOf(getString(R.string.multiple_choice_cloze_example_title)),
                        String.valueOf(getString(R.string.multiple_choice_cloze_example_body)),
                        (ArrayList<String>) exampleGroup,
                        String.valueOf(getString(R.string.multiple_choice_cloze_example_answer))
                );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_uoe_example_question, container, false);

        TextView exampleBody = view.findViewById(R.id.uoe_example_body);

        exampleBody.setText(multipleChoiceClozeQuestion.getBody());

        TextView exampleTitle = view.findViewById(R.id.uoe_example_title);

        exampleTitle.setText(multipleChoiceClozeQuestion.getTitle());

        return view;
    }

    @Override
    public boolean handleOnBackPressed() {

        return false;
    }

}

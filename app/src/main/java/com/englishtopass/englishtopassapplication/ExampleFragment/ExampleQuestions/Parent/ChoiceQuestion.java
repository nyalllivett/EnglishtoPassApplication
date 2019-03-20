package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.Parent;


import android.os.Bundle;
import android.view.View;

import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.GrandParent.BaseQuestion;

public class ChoiceQuestion extends BaseQuestion implements View.OnClickListener {

    public ChoiceQuestion() {
        // Required empty public constructor
    }

    public static ChoiceQuestion newInstance(String param1, String param2) {

        return new ChoiceQuestion();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}

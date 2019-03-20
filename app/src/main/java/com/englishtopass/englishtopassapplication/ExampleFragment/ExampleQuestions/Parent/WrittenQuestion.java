package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.Parent;


import android.os.Bundle;

import android.view.View;

import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.GrandParent.BaseQuestion;


public class WrittenQuestion extends BaseQuestion implements View.OnClickListener {

    public WrittenQuestion() {
        // Required empty public constructor
    }


    public static WrittenQuestion newInstance(String param1, String param2) {

        return new WrittenQuestion();
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

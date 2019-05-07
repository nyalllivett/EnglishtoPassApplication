package com.englishtopass.englishtopassapplication.QuestionFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.R;

public class MultipleSituationsQuestion extends Fragment {

    public MultipleSituationsQuestion() {
        // Required empty public constructor
    }

    public static MultipleSituationsQuestion newInstance(String param1, String param2) {
        MultipleSituationsQuestion fragment = new MultipleSituationsQuestion();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_situations_question, container, false);
    }



}

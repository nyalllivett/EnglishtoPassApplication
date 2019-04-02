package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceClozeQuestion extends Fragment implements OnBackPressedCallback {

    public MultipleChoiceClozeQuestion() {
        // Required empty public constructor
    }


    public static MultipleChoiceClozeQuestion newInstance() {
        MultipleChoiceClozeQuestion fragment = new MultipleChoiceClozeQuestion();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().addOnBackPressedCallback(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_choice_cloze_question, container, false);
    }



    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public boolean handleOnBackPressed() {

        getFragmentManager().popBackStack();

        return true;

    }
}

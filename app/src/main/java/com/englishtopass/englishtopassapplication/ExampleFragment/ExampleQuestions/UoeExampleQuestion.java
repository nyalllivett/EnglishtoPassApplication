package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions;


import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.MovableFloatingActionButton;
import com.englishtopass.englishtopassapplication.R;

public class UoeExampleQuestion extends Fragment implements OnBackPressedCallback {
    private static final String TAG = "UoeExampleQuestion";


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


        MovableFloatingActionButton movableFloatingActionButton = new MovableFloatingActionButton(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: hello");
        return inflater.inflate(R.layout.fragment_uoe_example_question, container, false);
    }

    @Override
    public boolean handleOnBackPressed() {

        return false;
    }

}

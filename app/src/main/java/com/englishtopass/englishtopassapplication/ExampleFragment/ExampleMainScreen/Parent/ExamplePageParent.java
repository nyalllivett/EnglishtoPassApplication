package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.Parent;


import android.annotation.SuppressLint;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.transition.TransitionSet;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Enums.QuestionType;
import com.englishtopass.englishtopassapplication.R;


public class ExamplePageParent extends Fragment implements OnBackPressedCallback {
    private static final String TAG = "ExamplePageParent";

    protected Button seeExampleButton, startTestButton;

    protected TextView exampleDescriptionTextView, examplePartType;

    protected ViewGroup rootConstraintLayout;

    protected boolean layoutChanged = false, exampleQuestionOpen = false, transitionRunning, toTransitionRunning = false;

    protected TransitionDrawable transitionDrawable;

    protected TransitionSet toTransitionSet, backTransitionSet;

    protected ConstraintSet constraintSetBeforeExample, constraintSetAfterExample;

    protected FrameLayout frameLayout;

    protected String testType, descriptionFromResources, partFromResources;

    protected androidx.appcompat.widget.Toolbar toolbar;

    protected FragmentManager fragmentManager;

    protected QuestionType QUESTION_TYPE;
    protected int UOE_PACKAGE_ID;
    protected int QUESTION_CHILDREN;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    protected void setActionBar() {

        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();

        if (appCompatActivity != null) {

            appCompatActivity.setSupportActionBar(toolbar);

            ActionBar actionBar = appCompatActivity.getSupportActionBar();

            actionBar.setHomeButtonEnabled(true);

            setHasOptionsMenu(true);

            actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setSubtitle(getString(R.string.uoe_test_title));
        }

    }


    protected void setConstraintLayouts() {

        // Initializing the Constraint set of the root set -
        constraintSetBeforeExample = new ConstraintSet();

        // Cloning the root set -
        constraintSetBeforeExample.clone((ConstraintLayout) rootConstraintLayout);

        // Initializing the Constraint set for the new set of constraints -
        constraintSetAfterExample = new ConstraintSet();

        // Cloning the new set -
        constraintSetAfterExample.clone(getContext(), R.layout.example_fragment_sub_set);


    }

    protected void addFrameLayout() {

        // Creating the frame layout for the new example fragment to appear in -
        frameLayout = new FrameLayout(getContext());

        // Generating a view id for the frame layout so i can reference it -
        int frameLayoutId = View.generateViewId();

        // Setting the frame layouts id -
        frameLayout.setId(frameLayoutId);

        // Setting the layout params, its working without these. Im not sure if the there are constraints it automatically sets to MATCH CONSTRAINTS -
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(0,0));

        // Adding in the new view to the constraint layout -
        rootConstraintLayout.addView(frameLayout);

        // Setting the new constraints for the new frame layout -
        constraintSetAfterExample.connect(frameLayout.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 32);
        constraintSetAfterExample.connect(frameLayout.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraintSetAfterExample.connect(frameLayout.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        constraintSetAfterExample.connect(frameLayout.getId(), ConstraintSet.BOTTOM, R.id.uoeSeeExampleButton, ConstraintSet.TOP, 32);

    }

    @Override
    public boolean handleOnBackPressed() {

        if (layoutChanged && exampleQuestionOpen || toTransitionRunning) {

            Log.d(TAG, "handleOnBackPressed: hello");
            seeExampleButton.callOnClick();

            return true;

        }

        Log.d(TAG, "handleOnBackPressed: bye");

        return false;

    }

    @Override
    public void onDestroy() {

        getActivity().removeOnBackPressedCallback(this);

        super.onDestroy();

    }

    // TODO: 20/03/2019 fix
    @SuppressLint("RestrictedApi")
    @Override
    public void onResume() {

        if (toTransitionRunning) {

            toTransitionSet.resume(rootConstraintLayout);

        }

        super.onResume();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onPause() {

        if (toTransitionRunning) {

            toTransitionSet.pause(rootConstraintLayout);

        }

        super.onPause();
    }

}

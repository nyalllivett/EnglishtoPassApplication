package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.UoeExampleQuestion;
import com.englishtopass.englishtopassapplication.R;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.ChangeBounds;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;


public class MainExampleFragment extends Fragment implements View.OnClickListener, OnBackPressedCallback{
    private static final String TAG = "MainExampleFragment";

    private int QUESTION_TYPE;

    private Button seeExampleButton, startTestButton;

    private TextView exampleDescriptionTextView, examplePartType;

    private ViewGroup rootConstraintLayout;

    private boolean layoutChanged = false, exampleQuestionOpen = false, transitionRunning, prematureFinish, toTransitionRunning = false;
    ;

    private TransitionSet toTransitionSet, backTransitionSet;

    private ConstraintSet constraintSetBeforeExample, constraintSetAfterExample;

    private FrameLayout frameLayout;

    private String testType, descriptionFromResources, partFromResources;

    private androidx.appcompat.widget.Toolbar toolbar;

    private FragmentManager fragmentManager;


    public MainExampleFragment() {
        // Required empty public constructor
    }

    // Setting the arguments into the bundle

    public static MainExampleFragment newInstance(int i) {

        MainExampleFragment fragment = new MainExampleFragment();

        Bundle bundle = new Bundle();

        bundle.putInt("QUESTION_TYPE", i);

        fragment.setArguments(bundle);

        return fragment;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().addOnBackPressedCallback(getViewLifecycleOwner(), this);

    }

    // Retrieving the arguments from the bundle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            QUESTION_TYPE = getArguments().getInt("QUESTION_TYPE");

        }

        // Switch statement on which morph_example_button is being run to then populate the text view accordingly -
        settingForTextViews();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.example_fragment, container, false);

        // Setting the toolbar -
        toolbar = view.findViewById(R.id.exampleToolbar);

        // Setting attributes for toolbar -
        setActionBar();

        // Constraint layout

        // Setting the root layout -
        rootConstraintLayout = view.findViewById(R.id.rootConstraintLayout);

        setConstraintLayouts();

        // The text views

        exampleDescriptionTextView = view.findViewById(R.id.testPartDescription);

        exampleDescriptionTextView.setText(descriptionFromResources);

        examplePartType = view.findViewById(R.id.testPartName);

        examplePartType.setText(partFromResources);

        // The buttons

        seeExampleButton = view.findViewById(R.id.uoeSeeExampleButton);

        seeExampleButton.setOnClickListener(this);

        startTestButton = view.findViewById(R.id.uoeStartTestButton);

        startTestButton.setOnClickListener(this);

        return view;
    }

    private void setConstraintLayouts() {

        // Initializing the Constraint set of the root set -
        constraintSetBeforeExample = new ConstraintSet();

        // Cloning the root set -
        constraintSetBeforeExample.clone((ConstraintLayout) rootConstraintLayout);

        // Initializing the Constraint set for the new set of constraints -
        constraintSetAfterExample = new ConstraintSet();

        // Cloning the new set -
        constraintSetAfterExample.clone(getContext(), R.layout.example_fragment_sub_set);


    }

    private void setActionBar() {

        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();

        if (appCompatActivity != null) {

            appCompatActivity.setSupportActionBar(toolbar);

            ActionBar actionBar = appCompatActivity.getSupportActionBar();

            actionBar.setHomeButtonEnabled(true);

            actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setSubtitle(testType);
        }

    }

    private void settingForTextViews() {

        String packageName = getContext().getPackageName();

        switch (QUESTION_TYPE) {

            case 1:

                testType = (String) getResources().getText(getResources().getIdentifier("uoe_test_title", "string", packageName));

                partFromResources = (String) getResources().getText(getResources().getIdentifier("part_number_one", "string", packageName));

                descriptionFromResources = (String) getResources().getText(getResources().getIdentifier("uoe_part_one_description", "string", packageName));

                break;

            case 2:

                testType = (String) getResources().getText(getResources().getIdentifier("listening_test_title", "string", packageName));

                partFromResources = (String) getResources().getText(getResources().getIdentifier("part_number_two", "string", packageName));

                descriptionFromResources = (String) getResources().getText(getResources().getIdentifier("listening_part_one_description", "string", packageName));

                break;

            case 3:

                break;

            case 4:

                break;

            default:

                break;

        }

    }


    @Override
    public void onClick(View v) {

        // TODO: 15/03/2019 change
        seeExampleButton.setClickable(false);

        switch (v.getId()) {

            case R.id.uoeSeeExampleButton:

                if (!layoutChanged) {

                    changeToExamplePageLayout();

                    break;

                } else {

                    changeBackExamplePageLayout();

                }

                break;

            case R.id.uoeStartTestButton:


                break;

        }

    }





    private void changeToExamplePageLayout() {

        /**
         * setting up the animations
         */

        toTransitionSet = new TransitionSet().setOrdering(TransitionSet.ORDERING_SEQUENTIAL)
                .addTransition(new Fade().setDuration(150))
                .addTransition(new ChangeBounds().setDuration(150))
                .setDuration(300)
                .addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(@NonNull Transition transition) {

                        transitionRunning = true;
                        toTransitionRunning = true;
                    }

                    @Override
                    public void onTransitionEnd(@NonNull Transition transition) {

                        if (layoutChanged && transitionRunning) {


                            addExampleQuestionFragment();

                            return;
                        }

                        transitionRunning = false;
                        toTransitionRunning = false;
                    }

                    @Override
                    public void onTransitionCancel(@NonNull Transition transition) {

                        Log.d(TAG, "onTransitionCancel: cancel");

                    }

                    @Override
                    public void onTransitionPause(@NonNull Transition transition) {

                        Log.d(TAG, "onTransitionPause: pause");

                    }

                    @Override
                    public void onTransitionResume(@NonNull Transition transition) {

                        Log.d(TAG, "onTransitionResume: resume");

                    }
                });

        TransitionManager.beginDelayedTransition(rootConstraintLayout, toTransitionSet);

        layoutChanged = true;

        // Changing the text to Back so the user knows the button will now revert the layout back to its original layout -
        seeExampleButton.setText("back");

        /**
         * Creating the new frame layout to house the fragment
         */

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


        // Applying the new constraint again to account for the new constraints -
        constraintSetAfterExample.applyTo((ConstraintLayout) rootConstraintLayout);



        /**
         * once the frame layout transition has complete, the fragment is creating in the on complete method in the transition set
         */

    }

    private void changeBackExamplePageLayout() {

        // Button cant be clicked until the the layout change has taken place -
        backTransitionSet = new TransitionSet().setOrdering(TransitionSet.ORDERING_SEQUENTIAL)
                .setStartDelay(200)
                .addTransition(new ChangeBounds().setDuration(150))
                .addTransition(new Fade().setDuration(150))
                .setDuration(300)
                .addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(@NonNull Transition transition) {

                        transitionRunning = true;

                    }

                    @Override
                    public void onTransitionEnd(@NonNull Transition transition) {

                        if (!layoutChanged) {

                            transitionRunning = false;

                        }
                        // Allowing the back to be clicked to revert back the layout -
                        seeExampleButton.setClickable(true);
                    }

                    @Override
                    public void onTransitionCancel(@NonNull Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(@NonNull Transition transition) {


                    }

                    @Override
                    public void onTransitionResume(@NonNull Transition transition) {

                    }
                });


        TransitionManager.beginDelayedTransition(rootConstraintLayout, backTransitionSet);

        // Changing the text to Back so the user knows the button will now revert the layout back to its original layout -
        seeExampleButton.setText(R.string.see_example);

        // Remove the example question fragment -
        if (exampleQuestionOpen) {

            fragmentManager.popBackStack();

        }

        // Removing the newly constructed frame layout -
        rootConstraintLayout.removeView(frameLayout);

        // Applying the original set of constraints to the root
        constraintSetBeforeExample.applyTo((ConstraintLayout) rootConstraintLayout);

        layoutChanged = false;

        exampleQuestionOpen = false;

    }



    private void addExampleQuestionFragment() {

        fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction
                .addToBackStack("uoe_example_question")
                .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_from_right, R.anim.slide_in_from_right, R.anim.slide_out_from_right)
                .add(frameLayout.getId(), new UoeExampleQuestion(), "uoe_example_question")
                .commit();


        exampleQuestionOpen = true;

        transitionRunning = false;

        prematureFinish = false;
        // Allowing the back to be clicked to revert back the layout -
        seeExampleButton.setClickable(true);

    }

    @Override
    public boolean handleOnBackPressed() {

        if (layoutChanged && exampleQuestionOpen || transitionRunning) {

            seeExampleButton.callOnClick();

            return true;

        }

        return false;

    }

    @Override
    public void onDestroy() {

        getActivity().removeOnBackPressedCallback(this);

        super.onDestroy();

    }

    @Override
    public void onResume() {

        if (!transitionRunning && prematureFinish) {

            addExampleQuestionFragment();

        }

        super.onResume();
    }

    @Override
    public void onPause() {
        if (transitionRunning && toTransitionRunning) {

            transitionRunning = false;
            prematureFinish = true;

        }

        super.onPause();
    }

}


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

    private Button seeExampleButton;
    private Button startTestButton;
    private TextView exampleDescriptionTextView;
    private TextView examplePartType;
    private ViewGroup rootConstraintLayout;
    private boolean layoutChanged = false;

    private boolean exampleQuestionOpen;

    private ConstraintSet constraintSetBeforeExample;
    private ConstraintSet constraintSetAfterExample;

    private FrameLayout frameLayout;

    private String testType;
    private String descriptionFromResources;
    private androidx.appcompat.widget.Toolbar toolbar;


    private FragmentManager fragmentManager;
    private String testPartName;

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

    @Override
    public void onDestroy() {

        getActivity().removeOnBackPressedCallback(this);

        super.onDestroy();
    }

    // Retrieving the arguments from the bundle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            QUESTION_TYPE = getArguments().getInt("QUESTION_TYPE");

        }

        // Setting this to false so when the back is pressed it acts as normal -
        exampleQuestionOpen = false;

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

        // Initializing the Constraint set of the root set -
        constraintSetBeforeExample = new ConstraintSet();

        // Cloning the root set -
        constraintSetBeforeExample.clone((ConstraintLayout) rootConstraintLayout);

        // The text views

        exampleDescriptionTextView = view.findViewById(R.id.testPartDescription);

        exampleDescriptionTextView.setText(descriptionFromResources);

        examplePartType = view.findViewById(R.id.testPartName);

        examplePartType.setText(testPartName);

        // The buttons

        seeExampleButton = view.findViewById(R.id.uoeSeeExampleButton);

        seeExampleButton.setOnClickListener(this);

        startTestButton = view.findViewById(R.id.uoeStartTestButton);

        startTestButton.setOnClickListener(this);

        return view;
    }

    private void setActionBar() {

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(testType);

    }

    private void settingForTextViews() {

        String packageName = getContext().getPackageName();

        switch (QUESTION_TYPE) {

            case 1:

                testType = (String) getResources().getText(getResources().getIdentifier("uoe_test_title", "string", packageName));

                testPartName = (String) getResources().getText(getResources().getIdentifier("part_number_one", "string", packageName));

                descriptionFromResources = (String) getResources().getText(getResources().getIdentifier("uoe_part_one_description", "string", packageName));

                break;

            case 2:

                testType = (String) getResources().getText(getResources().getIdentifier("listening_test_title", "string", packageName));

                testPartName = (String) getResources().getText(getResources().getIdentifier("part_number_two", "string", packageName));

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

        seeExampleButton.setClickable(false);


        if (constraintSetAfterExample == null) {

            // Initializing the Constraint set for the new set of constraints -
            constraintSetAfterExample = new ConstraintSet();

            // Cloning the new set -
            constraintSetAfterExample.clone(getContext(), R.layout.example_fragment_sub_set);

        }

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

                Log.d(TAG, "onClick: take morph_example_button");

                break;

        }

    }

    private void changeBackExamplePageLayout() {

        // Button cant be clicked until the the layout change has taken place -
        Log.d(TAG, "changeBackExamplePageLayout: clicked");

        TransitionManager.beginDelayedTransition(rootConstraintLayout,
                new TransitionSet().setOrdering(TransitionSet.ORDERING_SEQUENTIAL)
                        .setStartDelay(200)
                        .addTransition(new ChangeBounds().setDuration(150))
                        .addTransition(new Fade().setDuration(150))
                        .setDuration(300)
                        .addListener(new Transition.TransitionListener() {
                            @Override
                            public void onTransitionStart(@NonNull Transition transition) {

                            }

                            @Override
                            public void onTransitionEnd(@NonNull Transition transition) {
                                if (layoutChanged) {

                                    addExampleQuestionFragment();

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
                        })
        );



        // Changing the text to Back so the user knows the button will now revert the layout back to its original layout -
        seeExampleButton.setText(R.string.see_example);

        // Remove the example question fragment -
        fragmentManager.popBackStack();

        // Removing the newly constructed frame layout -
        rootConstraintLayout.removeView(frameLayout);

        // Applying the original set of constraints to the root
        constraintSetBeforeExample.applyTo((ConstraintLayout) rootConstraintLayout);

        // Now true we can start the process again if clicked -
        layoutChanged = false;
        exampleQuestionOpen = false;



    }

    private void changeToExamplePageLayout() {

        TransitionManager.beginDelayedTransition(rootConstraintLayout,
                new TransitionSet().setOrdering(TransitionSet.ORDERING_SEQUENTIAL)
                        .addTransition(new Fade().setDuration(150))
                        .addTransition(new ChangeBounds().setDuration(150))
                        .setDuration(300)
                        .addListener(new Transition.TransitionListener() {
                            @Override
                            public void onTransitionStart(@NonNull Transition transition) {

                            }

                            @Override
                            public void onTransitionEnd(@NonNull Transition transition) {
                                if (layoutChanged) {

                                    addExampleQuestionFragment();

                                }
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
                        })
        );


        // Now false when the back button is clicked this will trigger the revert of layout -
        layoutChanged = true;

        // Changing the text to Back so the user knows the button will now revert the layout back to its original layout -
        seeExampleButton.setText("back");

        // Applying the change in constraints -
        constraintSetAfterExample.applyTo((ConstraintLayout) rootConstraintLayout);

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
         *
         * ONCE THE TRANSITION OF CONSTRAINT LAYOUT IS COMPLETE THE ADD FRAGMENT IS CALLED IN ON COMPLETE CALLBACK IN THE TRANSITION
         *
         */

    }

    private void addExampleQuestionFragment() {

        fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction
                .addToBackStack("Testing")
                .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_from_right, R.anim.slide_in_from_right, R.anim.slide_out_from_right)
                .add(frameLayout.getId(), new UoeExampleQuestion(), "uoe_example_question")
                .commit();

        exampleQuestionOpen = true;


        // Allowing the back to be clicked to revert back the layout -
        seeExampleButton.setClickable(true);

    }

    @Override
    public boolean handleOnBackPressed() {

        if (layoutChanged && exampleQuestionOpen) {

            seeExampleButton.callOnClick();

            return true;

        }

        return false;

    }

}

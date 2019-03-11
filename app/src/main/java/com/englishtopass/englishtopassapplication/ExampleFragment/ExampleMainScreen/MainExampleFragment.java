package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen;



import android.animation.Animator;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.ChangeBounds;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.UoeExampleQuestion;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.TabbedFragments.UoETabbedFragment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class MainExampleFragment extends Fragment implements View.OnClickListener, OnBackPressedCallback{
    private static final String TAG = "MainExampleFragment";

    private int QUESTION_TYPE;

    private Button seeExampleButton;
    private Button startTestButton;
    private TextView exampleTitleTextView;
    private TextView exampleDescriptionTextView;
    private TextView examplePartNumber;
    private ViewGroup rootConstraintLayout;
    private boolean layoutChanged = false;

    private boolean exampleQuestionOpen;

    private ConstraintSet constraintSetBeforeExample;
    private ConstraintSet constraintSetAfterExample;

    private FrameLayout frameLayout;

    private String titleFromResources;
    private String descriptionFromResources;

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

        // Switch statement on which test is being run to then populate the text view accordingly -
        populateTextViews();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.example_fragment, container, false);

        // Constraint layout

        // Setting the root layout -
        rootConstraintLayout = view.findViewById(R.id.rootConstraintLayout);

        // Initializing the Constraint set of the root set -
        constraintSetBeforeExample = new ConstraintSet();

        // Cloning the root set -
        constraintSetBeforeExample.clone((ConstraintLayout) rootConstraintLayout);

        // The text views

        exampleTitleTextView = view.findViewById(R.id.testTitleAndTime);

        exampleTitleTextView.setText(titleFromResources);

        exampleDescriptionTextView = view.findViewById(R.id.testPartDescription);

        exampleDescriptionTextView.setText(descriptionFromResources);

        examplePartNumber = view.findViewById(R.id.testPartNumber);

        examplePartNumber.setText("Part - 1");

        // The buttons

        seeExampleButton = view.findViewById(R.id.uoeSeeExampleButton);

        seeExampleButton.setOnClickListener(this);

        startTestButton = view.findViewById(R.id.uoeStartTestButton);

        startTestButton.setOnClickListener(this);

        return view;
    }

    private void populateTextViews() {

        String packageName = getContext().getPackageName();

        switch (QUESTION_TYPE) {

            case 1:

                titleFromResources = (String) getResources().getText(getResources().getIdentifier("uoe_test_title", "string", packageName));

                descriptionFromResources = (String) getResources().getText(getResources().getIdentifier("uoe_part_one_description", "string", packageName));

                break;

            case 2:

                titleFromResources = (String) getResources().getText(getResources().getIdentifier("listening_test_title", "string", packageName));

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


        // Initializing the Constraint set for the new set of constraints -
        constraintSetAfterExample = new ConstraintSet();

        // Cloning the new set -
        constraintSetAfterExample.clone(getContext(), R.layout.example_fragment_sub_set);

        // Creating a new transition to attach to animation
        Transition transition = new ChangeBounds();

        // Setting the= length of transition -
        transition.setDuration(300);

        transition.addListener(new Transition.TransitionListener() {
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
        });

        // This is setting a transition ready for the animation, other wise it would change the constraints straight away with no animation  -
        TransitionManager.beginDelayedTransition(rootConstraintLayout, transition);


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

                Log.d(TAG, "onClick: take test");

                break;

        }

    }

    private void changeBackExamplePageLayout() {

        // Button cant be clicked until the the layout change has taken place -
        seeExampleButton.setClickable(false);

        // Changing the text to Back so the user knows the button will now revert the layout back to its original layout -
        seeExampleButton.setText("see example");

        // Remove the example question fragment -
        fragmentManager.popBackStack();

        // Removing the newly constructed frame layout -
        rootConstraintLayout.removeView(frameLayout);

        // Applying the original set of constraints to the root
        constraintSetBeforeExample.applyTo((ConstraintLayout) rootConstraintLayout);

        // Now true we can start the process again if clicked -
        layoutChanged = false;
        exampleQuestionOpen = false;

        // Allowing the back to be clicked to revert back the layout -
        seeExampleButton.setClickable(true);


    }

    private void changeToExamplePageLayout() {

        // Now false when the back button is clicked this will trigger the revert of layout -
        layoutChanged = true;

        // Button cant be clicked until the the layout change has taken place -
        seeExampleButton.setClickable(false);

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

        // Allowing the back to be clicked to revert back the layout -
        seeExampleButton.setClickable(true);

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

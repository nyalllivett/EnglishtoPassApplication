package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen;


import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.R;


public class MainExampleFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "UoeExampleFragment";

    private Button seeExampleButton;
    private Button startTestButton;
    private int QUESTION_TYPE;
    private TextView exampleTitleTextView;
    private TextView exampleDescriptionTextView;
    private TextView examplePartNumber;
    private ViewGroup rootConstraintLayout;
    private boolean layoutChanged = false;

    ConstraintSet constraintSetBeforeExample;

    ConstraintSet constraintSetAfterExample;

    FrameLayout frameLayout;

    private String title;
    private String description;

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

    // Retrieving the arguments from the bundle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            QUESTION_TYPE = getArguments().getInt("QUESTION_TYPE");

        }

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

        exampleTitleTextView.setText(title);

        exampleDescriptionTextView = view.findViewById(R.id.testPartDescription);

        exampleDescriptionTextView.setText(description);

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

                title = (String) getResources().getText(getResources().getIdentifier("uoe_test_title", "string", packageName));

                description = (String) getResources().getText(getResources().getIdentifier("uoe_part_one_description", "string", packageName));

                break;

            case 2:

                title = (String) getResources().getText(getResources().getIdentifier("listening_test_title", "string", packageName));

                description = (String) getResources().getText(getResources().getIdentifier("listening_part_one_description", "string", packageName));

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

        TransitionManager.beginDelayedTransition(rootConstraintLayout);


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

        // Removing the newly constructed frame layout -
        rootConstraintLayout.removeView(frameLayout);


        constraintSetBeforeExample.applyTo((ConstraintLayout) rootConstraintLayout);

        // Now true we can start the process again if clicked -
        layoutChanged = false;


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

        // This is setting a transition ready for the animation, other wise it would change the constraints straight away with no animation  -
//        TransitionManager.beginDelayedTransition(rootConstraintLayout);

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

        // Color for testing - WILL DELETE -
        frameLayout.setBackgroundColor(Color.parseColor("#bdbdbd"));

        // Adding in the new view to the constraint layout -
        rootConstraintLayout.addView(frameLayout);

        // Setting the new constraints for the new frame layout -
        constraintSetAfterExample.connect(frameLayout.getId(), ConstraintSet.TOP, R.id.testPartDescription, ConstraintSet.BOTTOM, 32);
        constraintSetAfterExample.connect(frameLayout.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraintSetAfterExample.connect(frameLayout.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        constraintSetAfterExample.connect(frameLayout.getId(), ConstraintSet.BOTTOM, R.id.uoeSeeExampleButton, ConstraintSet.TOP, 32);

        // Applying the new constraint again to account for the new constraints -
        constraintSetAfterExample.applyTo((ConstraintLayout) rootConstraintLayout);

        // Allowing the back to be clicked to revert back the layout -
        seeExampleButton.setClickable(true);

    }

}

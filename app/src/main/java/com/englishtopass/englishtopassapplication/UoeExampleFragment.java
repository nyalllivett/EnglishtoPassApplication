package com.englishtopass.englishtopassapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class UoeExampleFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "UoeExampleFragment";

    private Button seeExampleButton;
    private Button startTestButton;
    private int QUESTION_TYPE;
    private TextView exampleTitleTextView;
    private TextView exampleDescriptionTextView;
    private TextView examplePartNumber;

    private String title;
    private String description;

    public UoeExampleFragment() {
        // Required empty public constructor
    }

    // Setting the arguments into the bundle

    public static UoeExampleFragment newInstance(int i) {

        UoeExampleFragment fragment = new UoeExampleFragment();

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

        switch (QUESTION_TYPE) {

            case 1:

                title = (String) getResources().getText(getResources().getIdentifier("uoe_test_title", "string", getContext().getPackageName()));

                description = (String) getResources().getText(getResources().getIdentifier("uoe_part_one_description", "string", getContext().getPackageName()));

                break;

            case 2:

                title = (String) getResources().getText(getResources().getIdentifier("listening_test_title", "string", getContext().getPackageName()));

                description = (String) getResources().getText(getResources().getIdentifier("listening_part_one_description", "string", getContext().getPackageName()));

                break;

            case 3:


        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_uoe_example, container, false);

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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.uoeSeeExampleButton:

                Log.d(TAG, "onClick: see example");

                break;

            case R.id.uoeStartTestButton:

                Log.d(TAG, "onClick: take test");

                break;

        }

    }
}

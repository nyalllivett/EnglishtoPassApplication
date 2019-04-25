package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.Parent;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.reactivex.disposables.CompositeDisposable;


public class ExamplePageParent extends Fragment{
    private static final String TAG = "ExamplePageParent";

    protected Button seeExampleButton, startTestButton;

    protected TextView exampleDescriptionTextView, examplePartType;

    protected String descriptionFromResources, partFromResources;

    protected androidx.appcompat.widget.Toolbar toolbar;

    protected CompositeDisposable compositeDisposable;

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

}

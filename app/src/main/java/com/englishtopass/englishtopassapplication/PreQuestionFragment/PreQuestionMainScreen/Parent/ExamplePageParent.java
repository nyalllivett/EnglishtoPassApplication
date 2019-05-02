package com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen.Parent;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.question_menu, menu);
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

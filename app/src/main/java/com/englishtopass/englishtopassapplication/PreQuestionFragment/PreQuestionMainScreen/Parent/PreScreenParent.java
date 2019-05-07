package com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen.Parent;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Adapters.PreScreenAdapters.PreScreenModelAdapter;
import com.englishtopass.englishtopassapplication.Interfaces.OnRecyclerClick;
import com.englishtopass.englishtopassapplication.Model.ModelParent;
import com.englishtopass.englishtopassapplication.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.CompositeDisposable;


public class PreScreenParent extends Fragment{

    /**
     * buttons to start the test or start the example, i may remove the example one?
     */
    protected Button seeExampleButton, startTestButton;

    /**
     * instructions on how to complete the test
     * part type is for the toolbar subtitle where the type of part it is
     */
    protected TextView instructionsTextView, headerPartType;

    /**
     * these are the values for the above text views
     */
    protected String descriptionFromResources, partFromResources;

    /**
     * toolbar with different buttons
     */
    protected androidx.appcompat.widget.Toolbar toolbar;

    /**
     * fragment manager to load the question fragment and also pop back stack on
     * the toolbar back button
     */
    protected FragmentManager fragmentManager;

    /**
     * dispose of any unused thread calls
     */
    protected CompositeDisposable compositeDisposable;

    /**
     * group of views that will be faded in once the threads have ran there course
     */
    protected Group detailsGroup;

    /**
     * Recycler adapter
     */
    protected PreScreenModelAdapter adapter;

    /**
     * list of parts for the recycler view
     */
    protected List<ModelParent> modelParents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * Groups together straggling methods calls and initializing
     * @param view
     * the view thats getting inflated
     */
    protected void setViewUp(View view) {
        setActionBar(view.findViewById(R.id.preScreenToolbar));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext(), FlexDirection.COLUMN);

        recyclerView.setLayoutManager(flexboxLayoutManager);

        adapter = new PreScreenModelAdapter(getContext());

        recyclerView.setAdapter(adapter);

        detailsGroup = view.findViewById(R.id.group2);

        headerPartType = view.findViewById(R.id.testPartName);

        instructionsTextView = view.findViewById(R.id.testPartDescription);

        startTestButton = view.findViewById(R.id.uoeStartTestButton);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.question_menu, menu);
  }

    protected void setActionBar(Toolbar toolbar) {
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
            ActionBar actionBar = appCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
                setHasOptionsMenu(true);
                actionBar.setSubtitle(getString(R.string.uoe_test_title));
            }
        }
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}


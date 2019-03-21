package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen;


import android.app.Application;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.Adapters.ExamplePageRecyclerViewAdapter;
import com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDao;
import com.englishtopass.englishtopassapplication.Database.QuestionDatabase;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.Parent.ExamplePageParent;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.UoeQuestion;
import com.englishtopass.englishtopassapplication.MainActivityViewModel;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UoeCompletion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;
import com.englishtopass.englishtopassapplication.QuestionType;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.Repository.QuestionRepository;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

// TODO: 16/03/2019 Sort all this animation rubbish out ABSOLUTE WANK!
public class UoeExampleFragment extends ExamplePageParent implements View.OnClickListener, OnBackPressedCallback{
    private static final String TAG = "MainExampleFragment";

    private QuestionType QUESTION_TYPE;
    private int UOE_PACKAGE_ID;
    private int QUESTION_CHILDREN;


    public UoeExampleFragment() {
        // Required empty public constructor
    }

    // Setting the arguments into the bundle

    public static UoeExampleFragment newInstance(QuestionType questionType, int packageID, int packageChildren) {

        UoeExampleFragment fragment = new UoeExampleFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable("QUESTION_TYPE", questionType);
        bundle.putInt("UOE_PACKAGE_ID", packageID);
        bundle.putInt("QUESTION_CHILDREN", packageChildren);

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

            QUESTION_TYPE = (QuestionType) getArguments().getSerializable("QUESTION_TYPE");
            UOE_PACKAGE_ID = getArguments().getInt("UOE_PACKAGE_ID");
            QUESTION_CHILDREN = getArguments().getInt("QUESTION_CHILDREN");

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.example_fragment, container, false);

        // Toolbar

        toolbar = view.findViewById(R.id.exampleToolbar);

        setActionBar();

        // Recycler View -

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        final ExamplePageRecyclerViewAdapter adapter = new ExamplePageRecyclerViewAdapter(getContext());

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());

        flexboxLayoutManager.setFlexDirection(FlexDirection.COLUMN);

        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(flexboxLayoutManager);

        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getSingleUseOfEnglishPackage(UOE_PACKAGE_ID).observe(this, new Observer<UseOfEnglishPackage>() {
            @Override
            public void onChanged(final UseOfEnglishPackage useOfEnglishPackage) {


                 Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {

                        ArrayList<ModelUoeParent> arrayList = new ArrayList<>();

                        arrayList.add(useOfEnglishPackage.getWordFormationQuestion());
                        arrayList.add(useOfEnglishPackage.getMultipleChoiceClozeQuestion());
                        arrayList.add(useOfEnglishPackage.getKeywordTransformationQuestion());
                        arrayList.add(useOfEnglishPackage.getOpenClozeQuestion());




                        adapter.submitList(arrayList);
//                        settingForTextViews(useOfEnglishPackage.getUoeCompletion());
                    }
                }).observeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.d(TAG, "onSubscribe: on subscribe");

                    }

                    @Override
                    public void onComplete() {

                        Log.d(TAG, "onComplete: set text");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d(TAG, "onError: error");

                    }
                });

            }

        });


        // Constraint layout-

        rootConstraintLayout = view.findViewById(R.id.rootConstraintLayout);

        setConstraintLayouts();

        // The text views

        exampleDescriptionTextView = view.findViewById(R.id.testPartDescription);

        examplePartType = view.findViewById(R.id.testPartName);

        // The buttons

        seeExampleButton = view.findViewById(R.id.uoeSeeExampleButton);

        seeExampleButton.setOnClickListener(this);

        startTestButton = view.findViewById(R.id.uoeStartTestButton);

        startTestButton.setOnClickListener(this);

        return view;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d(TAG, "onOptionsItemSelected: here");
        switch (item.getItemId()) {

            case android.R.id.home:

                getActivity().getSupportFragmentManager().popBackStack();

        }

        return super.onOptionsItemSelected(item);
    }

    private void settingForTextViews(UoeCompletion uoeCompletion) {

        String packageName = getContext().getPackageName();

        switch (uoeCompletion) {

            case NOT_STARTED:

                testType = (String) getResources().getText(getResources().getIdentifier("uoe_test_title", "string", packageName));

                partFromResources = (String) getResources().getText(getResources().getIdentifier("uoe_part_one_title", "string", packageName));

                descriptionFromResources = (String) getResources().getText(getResources().getIdentifier("uoe_part_one_description", "string", packageName));

                break;

            case FIRST_COMPLETE:


                testType = (String) getResources().getText(getResources().getIdentifier("uoe_test_title", "string", packageName));

                partFromResources = (String) getResources().getText(getResources().getIdentifier("uoe_part_two_title", "string", packageName));

                descriptionFromResources = (String) getResources().getText(getResources().getIdentifier("uoe_part_two_description", "string", packageName));

                break;

                default:
                    Log.d(TAG, "settingForTextViews: oh dear");

        }

        exampleDescriptionTextView.setText(descriptionFromResources);

        examplePartType.setText(partFromResources);



    }

    @Override
    public void onClick(View v) {

        seeExampleButton.setClickable(false);

        switch (v.getId()) {

            case android.R.id.home:

                Log.d(TAG, "onClick: home");

                break;


            case R.id.uoeSeeExampleButton:

                if (!layoutChanged) {

                    changeToExamplePageLayout();

                    break;

                } else {

                    changeBackExamplePageLayout();

                }

                break;

            case R.id.uoeStartTestButton:

                Log.d(TAG, "onClick: hello");
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        UseOfEnglishDao useOfEnglishDao = QuestionDatabase.getInstance(getContext()).useOfEnglishDao();


                    }
                }).start();

                break;

        }

    }



    private void changeToExamplePageLayout() {


        transitionDrawable = (TransitionDrawable) seeExampleButton.getBackground();
        transitionDrawable.startTransition(300);


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

        seeExampleButton.setText(R.string.back);

        addFrameLayout();

        constraintSetAfterExample.applyTo((ConstraintLayout) rootConstraintLayout);

    }



    private void changeBackExamplePageLayout() {

        if (transitionDrawable != null ) {

            transitionDrawable.reverseTransition(300);

        }

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
                .add(frameLayout.getId(), UoeQuestion.newInstance(QUESTION_TYPE), "uoe_example_question")
                .commit();


        exampleQuestionOpen = true;

        transitionRunning = false;

        toTransitionRunning = false;

        // Allowing the back to be clicked to revert back the layout -
        seeExampleButton.setClickable(true);

    }


}



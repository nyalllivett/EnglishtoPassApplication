package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen;


import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.Adapters.ExampleAdapters.ExampleUoeAdapter;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.Parent.ExamplePageParent;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.UoeQuestion;
import com.englishtopass.englishtopassapplication.Enums.TestCompletion;
import com.englishtopass.englishtopassapplication.QuestionFragments.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.ViewModels.UoeViewModel;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;
import com.englishtopass.englishtopassapplication.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

// TODO: 16/03/2019 Sort all this animation rubbish out ABSOLUTE WANK!
public class UoeExampleFragment extends ExamplePageParent implements View.OnClickListener, OnBackPressedCallback {
    private static final String TAG = "MainExampleFragment";

    private TestCompletion testCompletion;
    private int packageId;

    public UoeExampleFragment() {
        // Required empty public constructor
    }

    public static UoeExampleFragment newInstance(TestCompletion testCompletion, int packageID) {

        UoeExampleFragment fragment = new UoeExampleFragment();

        Bundle bundle = new Bundle();

        // Enum
        bundle.putSerializable("testCompletion", testCompletion);

        // ID
        bundle.putInt("packageId", packageID);

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

            testCompletion = (TestCompletion) getArguments().getSerializable("testCompletion");
            packageId = getArguments().getInt("packageId");

        }

        compositeDisposable = new CompositeDisposable();

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

        final ExampleUoeAdapter adapter = new ExampleUoeAdapter(getContext());

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());

        flexboxLayoutManager.setFlexDirection(FlexDirection.COLUMN);

        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(flexboxLayoutManager);

        UoeViewModel uoeViewModel = ViewModelProviders.of(this).get(UoeViewModel.class);

        getSingle(uoeViewModel).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<UoeParent>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<UoeParent> uoeParents) {
                        adapter.submitList(uoeParents);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
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

        settingForTextViews(testCompletion);

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

    private void settingForTextViews(TestCompletion testCompletion) {

        switch (testCompletion) {

            case NOT_STARTED:

                partFromResources = getString(R.string.uoe_part_one_title);

                descriptionFromResources = getString(R.string.uoe_part_one_description);

                break;

            case FIRST_COMPLETE:

                partFromResources = getString(R.string.uoe_part_two_title);

                descriptionFromResources = getString(R.string.uoe_part_two_description);

                break;

            case SECOND_COMPLETE:

                partFromResources = getString(R.string.uoe_part_three_title);

                descriptionFromResources = getString(R.string.uoe_part_three_description);

                break;

            case THIRD_COMPLETE:

                partFromResources = getString(R.string.uoe_part_three_title);

                descriptionFromResources = getString(R.string.uoe_part_three_description);

                break;

            case PACKAGE_COMPLETE:

                Log.d(TAG, "settingForTextViews: complete");

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

                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .add(R.id.questionFragmentHolder, MultipleChoiceClozeQuestion.newInstance(), "MULTIPLE_CHOICE_QUESTION")
                        .addToBackStack("TAKE_QUESTION")
                        .commit();

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
                .add(frameLayout.getId(), UoeQuestion.newInstance(testCompletion), "uoe_example_question")
                .commit();


        exampleQuestionOpen = true;

        transitionRunning = false;

        toTransitionRunning = false;

        // Allowing the back to be clicked to revert back the layout -
        seeExampleButton.setClickable(true);

    }


    private Single<List<UoeParent>> getSingle(UoeViewModel uoeViewModel) {

        return  Single.zip(

                uoeViewModel.getMenuKeywordTransformation(packageId),
                uoeViewModel.getMenuMultipleChoiceQuestion(packageId),
                uoeViewModel.getMenuOpenClozeQuestion(packageId),
                uoeViewModel.getMenuWordFormationQuestion(packageId),
                ((keywordTransformationQuestion, multipleChoiceClozeQuestion, openClozeQuestion, wordFormationQuestion) -> {

                    List<UoeParent> arrayList = new ArrayList<>();
                    arrayList.add(keywordTransformationQuestion);
                    arrayList.add(multipleChoiceClozeQuestion);
                    arrayList.add(openClozeQuestion);
                    arrayList.add(wordFormationQuestion);

                    return arrayList;
                })
        );
    }

}



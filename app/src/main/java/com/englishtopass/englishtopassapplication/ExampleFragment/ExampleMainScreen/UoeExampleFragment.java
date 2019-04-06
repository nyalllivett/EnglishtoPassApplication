package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.Adapters.ExampleAdapters.ExampleUoeAdapter;
import com.englishtopass.englishtopassapplication.Enums.TestCompletion;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.Parent.ExamplePageParent;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.UoeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;
import com.englishtopass.englishtopassapplication.QuestionFragments.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.QuestionFragments.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.QuestionFragments.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.QuestionFragments.WordFormationQuestion;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.ViewModels.UoeViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UoeExampleFragment extends ExamplePageParent implements View.OnClickListener{
    private static final String TAG = "MainExampleFragment";

    private TestCompletion testCompletion;
    private int packageId;
    FragmentManager fragmentManager;

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

        fragmentManager = getActivity().getSupportFragmentManager();

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


        // The text views

        exampleDescriptionTextView = view.findViewById(R.id.testPartDescription);

        examplePartType = view.findViewById(R.id.testPartName);

        // The buttons

        seeExampleButton = view.findViewById(R.id.uoeSeeExampleButton);

        seeExampleButton.setOnClickListener(this);

        startTestButton = view.findViewById(R.id.uoeStartTestButton);

        startTestButton.setOnClickListener(this);

        settingForTextViews();

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

    private void settingForTextViews() {

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

        switch (v.getId()) {

            case android.R.id.home:

                Log.d(TAG, "onClick: home");

                break;


            case R.id.uoeSeeExampleButton:

                addExampleQuestionFragment();

                break;

            case R.id.uoeStartTestButton:

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.addToBackStack("TAKE_QUESTION")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .add(R.id.questionFragmentHolder, returnFragment(), "MULTIPLE_CHOICE_QUESTION")
                        .commit();

                break;

        }

    }

    private Fragment returnFragment() {

        switch (testCompletion){

            case NOT_STARTED:

                return MultipleChoiceClozeQuestion.newInstance(packageId);

            case FIRST_COMPLETE:

                return OpenClozeQuestion.newInstance(packageId);

            case SECOND_COMPLETE:

                return KeywordTransformationQuestion.newInstance(packageId);

            case THIRD_COMPLETE:

                return WordFormationQuestion.newInstance(packageId);

                default:
                    Log.d(TAG, "returnFragment: something went wrong");

        }

        return null;
    }


    private void addExampleQuestionFragment() {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction
                .addToBackStack("uoe_example_question")
                .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_from_right, R.anim.slide_in_from_right, R.anim.slide_out_from_right)
                .add(R.id.questionFragmentHolder, UoeQuestion.newInstance(testCompletion), "uoe_example_question")
                .commit();

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

    @Override
    public void onDestroy() {

        compositeDisposable.dispose();

        super.onDestroy();
    }
}



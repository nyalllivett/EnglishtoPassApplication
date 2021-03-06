package com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.Adapters.PreScreenAdapters.ExampleReadingAdapter;
import com.englishtopass.englishtopassapplication.Enums.TestCompletion;
import com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen.Parent.PreScreenParent;
import com.englishtopass.englishtopassapplication.PreQuestionFragment.ExampleQuestions.ListeningQuestion;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.ViewModels.ReadingViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.CompositeDisposable;


public class PreReadingScreen extends PreScreenParent implements View.OnClickListener {
    private static final String TAG = "ListeningExampleFragmen";

    private TestCompletion testCompletion;
    private int packageId;
    private FragmentManager fragmentManager;



    public PreReadingScreen() {
        // Required empty public constructor
    }

    public static PreReadingScreen newInstance(TestCompletion testCompletion, int packageID) {

        PreReadingScreen fragment = new PreReadingScreen();

        Bundle bundle = new Bundle();

        bundle.putSerializable("TEST_COMPLETION", testCompletion);
        bundle.putInt("LISTENING_PACKAGE_ID", packageID);

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

            testCompletion = (TestCompletion) getArguments().getSerializable("TEST_COMPLETION");
            packageId = getArguments().getInt("LISTENING_PACKAGE_ID");

        }

        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pre_question_screen, container, false);

        // Toolbar

        toolbar = view.findViewById(R.id.preScreenToolbar);

        setActionBar(((Toolbar) view.findViewById(R.id.preScreenToolbar)));

        // Recycler View -

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        final ExampleReadingAdapter adapter = new ExampleReadingAdapter(getContext());

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());

        flexboxLayoutManager.setFlexDirection(FlexDirection.COLUMN);

        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(flexboxLayoutManager);

        ReadingViewModel viewModel = ViewModelProviders.of(this).get(ReadingViewModel.class);

//        getSingle(viewModel).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<List<ReadingParent>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        compositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onSuccess(List<ReadingParent> listeningParents) {
//                        adapter.submitList(listeningParents);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });

        // The text views

        instructionsTextView = view.findViewById(R.id.testPartDescription);

        instructionsTextView.setText(descriptionFromResources);

        headerPartType = view.findViewById(R.id.testPartName);

        headerPartType.setText(partFromResources);

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

                partFromResources = getString(R.string.reading_part_one_title);

                descriptionFromResources = getString(R.string.reading_part_one_description);

                break;

            case FIRST_COMPLETE:

                partFromResources = getString(R.string.reading_part_two_title);

                descriptionFromResources = getString(R.string.reading_part_two_description);

                break;

            case SECOND_COMPLETE:

                partFromResources = getString(R.string.reading_part_three_title);

                descriptionFromResources = getString(R.string.reading_part_three_description);

                break;

            case PACKAGE_COMPLETE:

                Log.d(TAG, "settingForTextViews: complete");

                break;


            default:
                Log.d(TAG, "settingForTextViews: oh dear");

        }

        instructionsTextView.setText(descriptionFromResources);

        headerPartType.setText(partFromResources);

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


                break;

        }

    }

    private void addExampleQuestionFragment() {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction
                .addToBackStack("uoe_example_question")
                .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_from_right, R.anim.slide_in_from_right, R.anim.slide_out_from_right)
                .add(R.id.questionFragmentHolder, ListeningQuestion.newInstance(testCompletion), "uoe_example_question")
                .commit();

    }


//    private Single<List<ReadingParent>> getSingle(ReadingViewModel viewModel) {
//
//        return Single.zip(
//
////                viewModel.getMenuMultipleChoice(packageId),
////                viewModel.getMenuGappedText(packageId),
////                viewModel.getMenuMatchingExercise(packageId),
////                ((multipleChoiceQuestion, gappedTextQuestion, matchingExerciseQuestion) -> {
////
////                    List<ReadingParent> arrayList = new ArrayList<>();
////
////                    arrayList.add(matchingExerciseQuestion);
////                    arrayList.add(gappedTextQuestion);
////                    arrayList.add(matchingExerciseQuestion);
//
//                    return arrayList;
//
//                })
//
//        );
//    }

}






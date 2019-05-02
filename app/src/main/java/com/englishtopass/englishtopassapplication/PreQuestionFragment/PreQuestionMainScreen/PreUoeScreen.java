package com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.Adapters.ExampleAdapters.ExampleUoeAdapter;
import com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe;
import com.englishtopass.englishtopassapplication.Enums.TestCompletion;
import com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen.Parent.ExamplePageParent;
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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class PreUoeScreen extends ExamplePageParent implements View.OnClickListener{
    private static final String TAG = "PreUoeScreen";
    private QuestionPartUoe questionPartUoe;
    private int packageId;
    private FragmentManager fragmentManager;

    public PreUoeScreen() {
        // Required empty public constructor
    }

    public static PreUoeScreen newInstance(QuestionPartUoe questionPartUoe, int packageID) {
        PreUoeScreen fragment = new PreUoeScreen();
        Bundle bundle = new Bundle();
        bundle.putSerializable("questionPartUoe", questionPartUoe);
        bundle.putInt("packageId", packageID);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            questionPartUoe = (QuestionPartUoe) getArguments().getSerializable("questionPartUoe");
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

        view.findViewById(R.id.uoeSeeExampleButton).setOnClickListener(this);

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

        getSingle(uoeViewModel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

        examplePartType = view.findViewById(R.id.testPartName);

        exampleDescriptionTextView = view.findViewById(R.id.testPartDescription);


        // The buttons

        startTestButton = view.findViewById(R.id.uoeStartTestButton);

        startTestButton.setOnClickListener(this);

        setTextViews().subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

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

        switch (questionPartUoe) {

            case MULTIPLE_CHOICE_CLOZE:

                partFromResources = getString(R.string.uoe_part_one_title);

                descriptionFromResources = getString(R.string.uoe_part_one_description);


                break;

            case OPEN_CLOZE:

                partFromResources = getString(R.string.uoe_part_two_title);

                descriptionFromResources = getString(R.string.uoe_part_two_description);

                break;

            case KEYWORD_TRANSFORMATION:

                partFromResources = getString(R.string.uoe_part_three_title);

                descriptionFromResources = getString(R.string.uoe_part_three_description);

                break;

            case WORD_FORMATION:

                partFromResources = getString(R.string.uoe_part_four_title);

                descriptionFromResources = getString(R.string.uoe_part_four_description);

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


                Log.d(TAG, "onClick: clicked");

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

        switch (questionPartUoe){

            case MULTIPLE_CHOICE_CLOZE:

                return MultipleChoiceClozeQuestion.newInstance(packageId);

            case OPEN_CLOZE:

                return OpenClozeQuestion.newInstance(packageId);

            case KEYWORD_TRANSFORMATION:

                return KeywordTransformationQuestion.newInstance(packageId);

            case WORD_FORMATION:

                return WordFormationQuestion.newInstance(packageId);

                default:
                    Log.d(TAG, "returnFragment: something went wrong");

        }

        return null;
    }


    private Single<List<UoeParent>> getSingle(UoeViewModel viewModel) {

        return Single.zip(

                viewModel.getMenuMultipleChoiceQuestion(packageId),
                viewModel.getMenuOpenClozeQuestion(packageId),
                viewModel.getMenuKeywordTransformation(packageId),
                viewModel.getMenuWordFormationQuestion(packageId),
                (multipleChoiceCloze, openCloze, keywordTransformation, wordFormation) -> {

                    List<UoeParent> arrayList = new ArrayList<>();

                    arrayList.add(multipleChoiceCloze);
                    arrayList.add(openCloze);
                    arrayList.add(keywordTransformation);
                    arrayList.add(wordFormation);

                    return arrayList;

                }

        );
    }

    private Completable setTextViews(){

        return Completable.fromAction(() ->
                settingForTextViews()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onDestroy() {

        compositeDisposable.dispose();

        super.onDestroy();
    }
}



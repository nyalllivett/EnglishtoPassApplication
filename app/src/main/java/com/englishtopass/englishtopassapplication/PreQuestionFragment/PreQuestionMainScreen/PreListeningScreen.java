package com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.Adapters.PreScreenAdapters.PreScreenModelAdapter;
import com.englishtopass.englishtopassapplication.Interfaces.OnRecyclerClick;
import com.englishtopass.englishtopassapplication.Model.ModelParent;
import com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen.Parent.PreScreenParent;
import com.englishtopass.englishtopassapplication.PreQuestionFragment.ExampleQuestions.ListeningQuestion;
import com.englishtopass.englishtopassapplication.QuestionFragments.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.QuestionFragments.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.QuestionFragments.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.QuestionFragments.WordFormationQuestion;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.ViewModels.ListeningViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class PreListeningScreen extends PreScreenParent implements View.OnClickListener, OnRecyclerClick {
    private static final String TAG = "ListeningExampleFragmen";

    private int packageId;
    private ListeningViewModel viewModel;
    private Fragment fragment;


    public PreListeningScreen() {
        // Required empty public constructor
    }

    public static PreListeningScreen newInstance(int packageID) {
        PreListeningScreen fragment = new PreListeningScreen();
        Bundle bundle = new Bundle();
        bundle.putInt("packageId", packageID);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Retrieving the arguments from the bundle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        if (getArguments() != null) packageId = getArguments().getInt("packageId");
        viewModel = ViewModelProviders.of(this).get(ListeningViewModel.class);
        getQuestionModels();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pre_question_screen, container, false);
        super.setViewUp(view);
        startTestButton.setOnClickListener(this);
        adapter.setOnRecyclerClick(this);
        submitList();
        return view;
    }

    private void getQuestionModels() {

        viewModel.getAllSingles(packageId).subscribeOn(Schedulers.io())
                .retry(5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ModelParent>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<ModelParent> uoeParents) {
                        modelParents = uoeParents;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }
                });
    }

    private void submitList() {
        Completable.fromAction(() -> {
            if (modelParents == null || modelParents.size() <= 0)
                throw new ArrayIndexOutOfBoundsException("listening parents not ready");
        }).retryWhen(f -> f.take(30).delay(200, TimeUnit.MILLISECONDS))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }
                    @Override
                    public void onComplete() {
                        adapter.submitList(modelParents);
                        findNextPart(modelParents);
                        detailsGroup.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace(new PrintStream(System.out));
                    }
                });
    }

    private void findNextPart(List<ModelParent> modelParents) {
        for (ModelParent modelParent : modelParents) {
            if (!modelParent.isComplete()){
                refreshModel(modelParent);
                return;
            }
        }
    }

    private void refreshModel(ModelParent model){

        headerPartType.setText(model.getType());
        instructionsTextView.setText(model.getInstructions());

        switch (model.getType()) {

            case "Multiple Situations":
                fragment = MultipleChoiceClozeQuestion.newInstance(packageId);
                break;
            case "Blank Filling":
                fragment = OpenClozeQuestion.newInstance(packageId);
                break;
            case "Match Speakers":
                fragment = KeywordTransformationQuestion.newInstance(packageId);
                break;
            case "One Situation":
                fragment = WordFormationQuestion.newInstance(packageId);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                fragmentManager.popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.home:
                break;
            case R.id.uoeSeeExampleButton:
                break;
            case R.id.uoeStartTestButton:
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.questionFragmentHolder, fragment, "QUESTION_FRAGMENT")
                        .addToBackStack("QUESTION_FRAGMENT")
                        .commit();
                break;
        }
    }

    @Override
    public void onItemClick(int id) {
        refreshModel(modelParents.get(id));
    }
}
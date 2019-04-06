package com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.Adapters.ExampleAdapters.ExampleListeningAdapter;
import com.englishtopass.englishtopassapplication.Enums.TestCompletion;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.Parent.ExamplePageParent;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleQuestions.ListeningQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.Parent.ListeningParent;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.ViewModels.ListeningViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


public class ListeningExampleFragment extends ExamplePageParent implements View.OnClickListener {
    private static final String TAG = "ListeningExampleFragmen";

    private TestCompletion testCompletion;
    private int packageId;
    private FragmentManager fragmentManager;


    public ListeningExampleFragment() {
        // Required empty public constructor
    }

    public static ListeningExampleFragment newInstance(TestCompletion testCompletion, int packageID) {

        ListeningExampleFragment fragment = new ListeningExampleFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable("TEST_COMPLETION", testCompletion);
        bundle.putInt("LISTENING_PACKAGE_ID", packageID);

        fragment.setArguments(bundle);

        return fragment;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();
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

        final ExampleListeningAdapter adapter = new ExampleListeningAdapter(getContext());

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());

        flexboxLayoutManager.setFlexDirection(FlexDirection.COLUMN);

        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(flexboxLayoutManager);

        ListeningViewModel viewModel = ViewModelProviders.of(this).get(ListeningViewModel.class);

        getSingle(viewModel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ListeningParent>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<ListeningParent> listeningParents) {
                        adapter.submitList(listeningParents);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


        // The text views

        exampleDescriptionTextView = view.findViewById(R.id.testPartDescription);

        exampleDescriptionTextView.setText(descriptionFromResources);

        examplePartType = view.findViewById(R.id.testPartName);

        examplePartType.setText(partFromResources);

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

                partFromResources = getString(R.string.listening_part_one_title);

                descriptionFromResources = getString(R.string.listening_part_one_description);

                break;

            case FIRST_COMPLETE:

                partFromResources = getString(R.string.listening_part_two_title);

                descriptionFromResources = getString(R.string.listening_part_two_description);

                break;

            case SECOND_COMPLETE:

                partFromResources = getString(R.string.listening_part_three_title);

                descriptionFromResources = getString(R.string.listening_part_three_description);

                break;

            case THIRD_COMPLETE:

                partFromResources = getString(R.string.listening_part_three_title);

                descriptionFromResources = getString(R.string.listening_part_three_description);

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


                break;

        }

    }






    private void addExampleQuestionFragment() {

        fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction
                .addToBackStack("uoe_example_question")
                .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_from_right, R.anim.slide_in_from_right, R.anim.slide_out_from_right)
                .add(R.id.questionFragmentHolder, ListeningQuestion.newInstance(testCompletion), "uoe_example_question")
                .commit();

    }


    private Single<List<ListeningParent>> getSingle(ListeningViewModel viewModel) {

        return Single.zip(

                viewModel.getMenuBlankFilling(packageId),
                viewModel.getMenuListeningMultiple(packageId),
                viewModel.getMenuListeningOne(packageId),
                viewModel.getMenuMatchSpeakers(packageId),
                ((blankFillingQuestion, listeningMultipleSituationsQuestion, listeningOneSituationQuestion, matchSpeakersQuestion) -> {

                    List<ListeningParent> arrayList = new ArrayList<>();

                    arrayList.add(listeningMultipleSituationsQuestion);
                    arrayList.add(blankFillingQuestion);
                    arrayList.add(matchSpeakersQuestion);
                    arrayList.add(listeningOneSituationQuestion);

                    return arrayList;

                })

        );
    }

}






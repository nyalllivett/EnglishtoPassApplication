package com.englishtopass.englishtopassapplication.ViewModels;

import android.app.Application;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningMultipleSituationsQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.Parent.ListeningParent;
import com.englishtopass.englishtopassapplication.Model.ModelParent;
import com.englishtopass.englishtopassapplication.Repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.Single;

public class ListeningViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;

    private LiveData<List<ListeningPackage>> listeningPackageLiveData;

    public ListeningViewModel(@NonNull Application application) {
        super(application);

        questionRepository = new QuestionRepository(application);

        listeningPackageLiveData = questionRepository.getAllListeningPackages();
    }

    public Single<List<ModelParent>> getAllSingles(int id) {

        return Single.zip(
                questionRepository.getMenuMultipleSituations(id),
                questionRepository.getMenuBlankFilling(id),
                questionRepository.getMenuMatchSpeakers(id),
                questionRepository.getMenuOneSituation(id),
                ((listeningMultipleSituationsQuestion, blankFillingQuestion, matchSpeakersQuestion, listeningOneSituationQuestion) -> {

                    ArrayList<ModelParent> arrayList = new ArrayList<>();

                    arrayList.add(listeningMultipleSituationsQuestion);
                    arrayList.add(blankFillingQuestion);
                    arrayList.add(matchSpeakersQuestion);
                    arrayList.add(listeningOneSituationQuestion);

                    return arrayList;

                })

        );
    }

    public LiveData<List<ListeningPackage>> getListeningPackageLiveData() {
        return listeningPackageLiveData;
    }


}

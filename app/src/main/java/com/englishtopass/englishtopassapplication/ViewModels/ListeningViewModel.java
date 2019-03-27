package com.englishtopass.englishtopassapplication.ViewModels;

import android.app.Application;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningMultipleSituationsQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;
import com.englishtopass.englishtopassapplication.Repository.QuestionRepository;

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

    public LiveData<List<ListeningPackage>> getListeningPackageLiveData() {
        return listeningPackageLiveData;
    }

    // RETRIEVE SINGLE LISTENING QUESTION DATA -
    public Single<ListeningMultipleSituationsQuestion> getMenuListeningMultiple(int id) {
        return questionRepository.getMenuMulipleSituations(id);
    }

    public Single<BlankFillingQuestion> getMenuBlankFilling(int id) {
        return questionRepository.getMenuBlankFilling(id);

    }

    public Single<MatchSpeakersQuestion> getMenuMatchSpeakers(int id) {
        return questionRepository.getMenuMatchSpeakers(id);
    }

    public Single<ListeningOneSituationQuestion> getMenuListeningOne(int id) {
        return questionRepository.getMenuOneSituation(id);

    }


}

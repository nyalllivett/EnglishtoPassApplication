package com.englishtopass.englishtopassapplication;

import android.app.Application;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;
import com.englishtopass.englishtopassapplication.Repository.QuestionRepository;

import java.security.Key;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.Single;

public class MainActivityViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;

    private LiveData<List<UseOfEnglishPackage>> useOfEnglishPackageLiveData;
    private LiveData<UseOfEnglishPackage> singleUseOfEnglishPackageLiveData;

    private LiveData<List<ListeningPackage>> listeningPackageLiveData;
    private LiveData<ListeningPackage> singleListeningPackageLiveData;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        questionRepository = new QuestionRepository(application);

        useOfEnglishPackageLiveData = questionRepository.getAllUseOfEnglishPackages();

        listeningPackageLiveData = questionRepository.getAllListeningPackages();

    }

    // RETRIEVE ALL PACKAGES -
    public LiveData<List<UseOfEnglishPackage>> getUseOfEnglishPackageLiveData() {
        return useOfEnglishPackageLiveData;
    }

    public LiveData<List<ListeningPackage>> getListeningPackageLiveData() {
        return listeningPackageLiveData;
    }

    // RETRIEVE SINGLE PACKAGE LIVE DATA -
    public LiveData<UseOfEnglishPackage> getSingleUseOfEnglishPackage(int id) {
        return singleUseOfEnglishPackageLiveData = questionRepository.getSingleUseOfEnglishPackages(id);
    }

    public LiveData<ListeningPackage> getSingleListeningPackage(int id) {
        return singleListeningPackageLiveData = questionRepository.getSingleListeningPackages(id);
    }


    // RETRIEVE SINGLE UOE QUESTION DATA -
    public Single<MultipleChoiceClozeQuestion> getMenuMultipleChoiceQuestion(int id) {
        return questionRepository.getMenuMultipleChoice(id);
    }

    public Single<OpenClozeQuestion> getMenuOpenClozeQuestion(int id) {
        return questionRepository.getMenuOpenCloze(id);

    }

    public Single<KeywordTransformationQuestion> getMenuKeywordTransformation(int id) {
        return questionRepository.getMenuKeywordQuestion(id);
    }

    public Single<WordFormationQuestion> getMenuWordFormationQuestion(int id) {
        return questionRepository.getMenuWordFormation(id);

    }


}

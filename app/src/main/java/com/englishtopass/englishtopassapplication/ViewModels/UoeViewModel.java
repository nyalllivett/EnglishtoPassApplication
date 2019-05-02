package com.englishtopass.englishtopassapplication.ViewModels;

import android.app.Application;

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

public class UoeViewModel extends AndroidViewModel {
    private static final String TAG = "UoeViewModel";

    private QuestionRepository questionRepository;

    private LiveData<List<UseOfEnglishPackage>> useOfEnglishPackageLiveData;

    private long savedTimeElapsed = 0L;

    private boolean questionStarted;

    public UoeViewModel(@NonNull Application application) {
        super(application);

        questionRepository = new QuestionRepository(application);

        useOfEnglishPackageLiveData = questionRepository.getAllUseOfEnglishPackages();


    }


    public long getSavedTimeElapsed() {
        return savedTimeElapsed;
    }

    public void setSavedTimeElapsed(long savedTimeElapsed) {
        this.savedTimeElapsed = savedTimeElapsed;
    }

    public boolean isQuestionStarted() {
        return questionStarted;
    }

    public void setQuestionStarted(boolean questionStarted) {
        this.questionStarted = questionStarted;
    }

    /**
     * The live data for the Actual question
     */

    public LiveData<MultipleChoiceClozeQuestion> getMultipleChoiceClozeLiveData(int id) {
        return questionRepository.getMultipleChoiceClozeLiveData(id);
    }

    public LiveData<OpenClozeQuestion> getOpenClozeQuestionLiveData(int id) {
        return questionRepository.getOpenClozeQuestionLiveData(id);
    }

    public LiveData<KeywordTransformationQuestion> getKeywordTransformationLiveData(int id) {
        return questionRepository.getKeywordTransformationQuestionLiveData(id);
    }

    public LiveData<WordFormationQuestion> getWordFormationLiveData(int id) {
        return questionRepository.getWordFormationQuestionLiveData(id);
    }





    // RETRIEVE ALL PACKAGES -
    public LiveData<List<UseOfEnglishPackage>> getUseOfEnglishPackageLiveData() {
        return useOfEnglishPackageLiveData;
    }

    public void updateMultipleChoice(MultipleChoiceClozeQuestion multipleChoiceClozeQuestion) {
        questionRepository.updateMultipleChoiceCloze(multipleChoiceClozeQuestion);

    }



    // RETRIEVE SINGLE UOE QUESTION DATA -
    public Single<MultipleChoiceClozeQuestion> getMenuMultipleChoiceQuestion(int id) {
        return questionRepository.getMenuMultipleChoiceCloze(id);
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

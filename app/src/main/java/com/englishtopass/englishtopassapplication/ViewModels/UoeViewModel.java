package com.englishtopass.englishtopassapplication.ViewModels;

import android.app.Application;

import com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe;
import com.englishtopass.englishtopassapplication.Model.ModelParent;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;
import com.englishtopass.englishtopassapplication.QuestionFragments.Question;
import com.englishtopass.englishtopassapplication.Repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Single;
import io.reactivex.functions.Function4;

public class UoeViewModel extends AndroidViewModel {
    private static final String TAG = "UoeViewModel";

    private QuestionRepository questionRepository;
    private LiveData<List<UseOfEnglishPackage>> useOfEnglishPackageLiveData;
    private long savedTimeElapsed = 0L;
    private boolean questionStarted = false;

    public UoeViewModel(@NonNull Application application) {
        super(application);
        questionRepository = new QuestionRepository(application);
        useOfEnglishPackageLiveData = questionRepository.getAllUseOfEnglishPackages();
    }

    /**
     * returns the live data for the package
     */
    public LiveData<List<UseOfEnglishPackage>> getUseOfEnglishPackageLiveData() {
        return useOfEnglishPackageLiveData;
    }

    /**
     * these methods are to save the time spent in the question through state changes
     */
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
     * The live data for the single questions
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


    /**
     * the rx calls for the single questions on the pre screen
     */

    public Single<List<ModelParent>> getAllSingles(int id){

        return Single.zip(
                questionRepository.getMenuMultipleChoiceCloze(id),
                questionRepository.getMenuOpenCloze(id),
                questionRepository.getMenuKeywordQuestion(id),
                questionRepository.getMenuWordFormation(id),
                ((multipleChoiceClozeQuestion, openClozeQuestion, keywordTransformationQuestion, wordFormationQuestion) -> {
                    ArrayList<ModelParent> arrayList = new ArrayList<>();
                    arrayList.add(multipleChoiceClozeQuestion);
                    arrayList.add(openClozeQuestion);
                    arrayList.add(keywordTransformationQuestion);
                    arrayList.add(wordFormationQuestion);
                    return arrayList;
                })
        );
    }

    /**
     * UPDATE CALLS
     */

    public void updateMultipleChoice(MultipleChoiceClozeQuestion multipleChoiceClozeQuestion) {
        questionRepository.updateMultipleChoiceCloze(multipleChoiceClozeQuestion);

    }

    public void updateUseOfEnglishPackage(UseOfEnglishPackage useOfEnglishPackage) {
        questionRepository.updateUseOfEnglishPackage(useOfEnglishPackage);

    }


}

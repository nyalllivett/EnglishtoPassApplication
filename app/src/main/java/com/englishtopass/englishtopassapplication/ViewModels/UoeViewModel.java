package com.englishtopass.englishtopassapplication.ViewModels;

import android.app.Application;
import android.util.Log;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
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
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Single;

public class UoeViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;

    private LiveData<List<UseOfEnglishPackage>> useOfEnglishPackageLiveData;

    private MutableLiveData<OpenClozeQuestion> mutableLiveData = new MutableLiveData<>();

    public UoeViewModel(@NonNull Application application) {
        super(application);

        questionRepository = new QuestionRepository(application);

        useOfEnglishPackageLiveData = questionRepository.getAllUseOfEnglishPackages();


    }



    public LiveData<OpenClozeQuestion> getOpenClozeQuestionMutableLiveData(int id) {

        return questionRepository.getOpenClozeQuestionLiveData(id);

    }


























    // RETRIEVE ALL PACKAGES -
    public LiveData<List<UseOfEnglishPackage>> getUseOfEnglishPackageLiveData() {
        return useOfEnglishPackageLiveData;
    }

    // RETRIEVE SINGLE PACKAGE -
    public UseOfEnglishPackage useOfEnglishPackage(int id) {
        return questionRepository.getSingleUseOfEnglishPackages(id);
    }

    public void updateUseOfEnglishPackage(UseOfEnglishPackage useOfEnglishPackage) {

        questionRepository.updateUseOfEnglishPackage(useOfEnglishPackage);

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

    public void updateMultipleChoice(MultipleChoiceClozeQuestion multipleChoiceClozeQuestion) {
        questionRepository.updateMultipleChoiceCloze(multipleChoiceClozeQuestion);

    }


}

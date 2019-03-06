package com.englishtopass.englishtopassapplication;

import android.app.Application;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Repository.QuestionRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;
    private LiveData<List<UseOfEnglishPackage>> useOfEnglishPackageLiveData;
    private LiveData<List<ListeningPackage>> listeningPackageLiveData;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        questionRepository = new QuestionRepository(application);

        useOfEnglishPackageLiveData = questionRepository.getAllUseOfEnglishPackages();

        listeningPackageLiveData = questionRepository.getAllListeningPackages();

    }

    public LiveData<List<UseOfEnglishPackage>> getUseOfEnglishPackageLiveData() {
        return useOfEnglishPackageLiveData;
    }

    public LiveData<List<ListeningPackage>> getListeningPackageLiveData() {
        return listeningPackageLiveData;
    }

}

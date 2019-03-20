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
    private LiveData<UseOfEnglishPackage> sinlgeUseOfEnglishPackageLiveData;

    private LiveData<List<ListeningPackage>> listeningPackageLiveData;
    private LiveData<ListeningPackage> singleListeningPackageLiveData;


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

    public LiveData<UseOfEnglishPackage> getSingleUseOfEnglishPackage(int id) {
        return sinlgeUseOfEnglishPackageLiveData = questionRepository.getSingleUseOfEnglishPackages(id);
    }

    public LiveData<ListeningPackage> getSingleListeningPackage(int id) {
        return singleListeningPackageLiveData = questionRepository.getSingleListeningPackages(id);
    }


}

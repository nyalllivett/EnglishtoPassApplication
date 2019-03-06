package com.englishtopass.englishtopassapplication.Repository;

import android.app.Application;

import com.englishtopass.englishtopassapplication.Dao.ListeningDao;
import com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDao;
import com.englishtopass.englishtopassapplication.Database.QuestionDatabase;
import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;

import java.util.List;

import androidx.lifecycle.LiveData;

public class QuestionRepository {

    private UseOfEnglishDao useOfEnglishDao;
    private ListeningDao listeningDao;

    private LiveData<List<UseOfEnglishPackage>> useOfEnglishLivePackages;
    private LiveData<List<ListeningPackage>> listeningPackages;


    // INIT
    public QuestionRepository(Application application) {

        /**
         * getting instance of database
         */
        QuestionDatabase questionDatabase = QuestionDatabase.getInstance(application);

        /**
         * setting the daos
         */
        useOfEnglishDao = questionDatabase.useOfEnglishDao();

        listeningDao = questionDatabase.listeningDao();

        /**
         * setting the live lists from the daos
         */
        useOfEnglishLivePackages = useOfEnglishDao.getAllUseOfEnglishPackages();

        listeningPackages = listeningDao.getAllListeningPackages();

    }

    /**
     * methods to return the live data
     */


    public LiveData<List<UseOfEnglishPackage>> getAllUseOfEnglishPackages(){

        return useOfEnglishLivePackages;

    }

    public LiveData<List<ListeningPackage>> getAllListeningPackages() {
        return listeningPackages;
    }
}

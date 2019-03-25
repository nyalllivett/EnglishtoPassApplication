package com.englishtopass.englishtopassapplication.Repository;

import android.app.Application;

import com.englishtopass.englishtopassapplication.Dao.KeywordTransformationDao;
import com.englishtopass.englishtopassapplication.Dao.ListeningDao;
import com.englishtopass.englishtopassapplication.Dao.MultipleChoiceClozeDao;
import com.englishtopass.englishtopassapplication.Dao.OpenClozeDao;
import com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDao;
import com.englishtopass.englishtopassapplication.Dao.WordFormationDao;
import com.englishtopass.englishtopassapplication.Database.QuestionDatabase;
import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Single;

public class QuestionRepository {

    private UseOfEnglishDao useOfEnglishDao;
    private ListeningDao listeningDao;
    private MultipleChoiceClozeDao multipleChoiceClozeDao;
    private OpenClozeDao openClozeDao;
    private KeywordTransformationDao keywordTransformationDao;
    private WordFormationDao wordFormationDao;

    private LiveData<List<UseOfEnglishPackage>> useOfEnglishLivePackages;
    private LiveData<UseOfEnglishPackage> singleUseOfEnglishPackage;

    private LiveData<List<ListeningPackage>> listeningPackages;
    private LiveData<ListeningPackage> singleListeningPackage;


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

            multipleChoiceClozeDao = questionDatabase.multipleChoiceClozeDao();

            openClozeDao = questionDatabase.openClozeDao();

            keywordTransformationDao = questionDatabase.keywordTransformationDao();

            wordFormationDao = questionDatabase.wordFormationDao();

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


    public LiveData<List<UseOfEnglishPackage>> getAllUseOfEnglishPackages() {

        return useOfEnglishLivePackages;

    }

    public LiveData<UseOfEnglishPackage> getSingleUseOfEnglishPackages(int i) {

        return useOfEnglishDao.getSingleUseOfEnglishPackage(i);

    }



    public LiveData<List<ListeningPackage>> getAllListeningPackages() {
        return listeningPackages;
    }


    public LiveData<ListeningPackage> getSingleListeningPackages(int i) {

        return listeningDao.getSingleListeningPackage(i);

    }

    /**
     UOE SINGLES
     */

    public Single<OpenClozeQuestion> getMenuOpenCloze(int i ) {

        return openClozeDao.getModelParentOpenCloze(i);

    }

    public Single<KeywordTransformationQuestion> getMenuKeywordQuestion(int i ) {

        return keywordTransformationDao.getModelParentKeyword(i);

    }

    public Single<MultipleChoiceClozeQuestion> getMenuMultipleChoice(int i ) {

        return multipleChoiceClozeDao.getModelParentMultipleChoice(i);

    }

    public Single<WordFormationQuestion> getMenuWordFormation(int i ) {

        return wordFormationDao.getModelParentWordFormation(i);

    }


}
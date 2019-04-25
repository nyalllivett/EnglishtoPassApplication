package com.englishtopass.englishtopassapplication.Repository;

import android.app.Application;

import com.englishtopass.englishtopassapplication.Dao.ListeningDaos.BlankFillingDao;
import com.englishtopass.englishtopassapplication.Dao.ListeningDaos.ListeningMultipleDao;
import com.englishtopass.englishtopassapplication.Dao.ListeningDaos.ListeningOneDao;
import com.englishtopass.englishtopassapplication.Dao.ListeningDaos.MatchSpeakersDao;
import com.englishtopass.englishtopassapplication.Dao.ReadingDao.GappedDao;
import com.englishtopass.englishtopassapplication.Dao.ReadingDao.MatchingExerciseDao;
import com.englishtopass.englishtopassapplication.Dao.ReadingDao.MultipleChoiceDao;
import com.englishtopass.englishtopassapplication.Dao.ReadingDao.ReadingDao;
import com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDaos.KeywordTransformationDao;
import com.englishtopass.englishtopassapplication.Dao.ListeningDaos.ListeningDao;
import com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDaos.MultipleChoiceClozeDao;
import com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDaos.OpenClozeDao;
import com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDaos.UseOfEnglishDao;
import com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDaos.WordFormationDao;
import com.englishtopass.englishtopassapplication.Database.QuestionDatabase;
import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningMultipleSituationsQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Package.ReadingPackage;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.GappedTextQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.MatchingExerciseQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.MultipleChoiceQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.Parent.ReadingParent;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Single;

public class QuestionRepository {

    // USE OF ENGLISH DAOS
    private UseOfEnglishDao useOfEnglishDao;

    private MultipleChoiceClozeDao multipleChoiceClozeDao;
    private OpenClozeDao openClozeDao;
    private KeywordTransformationDao keywordTransformationDao;
    private WordFormationDao wordFormationDao;

    // LISTENING DAOS
    private ListeningDao listeningDao;

    private ListeningMultipleDao listeningMultipleDao;
    private ListeningOneDao listeningOneDao;
    private MatchSpeakersDao matchSpeakersDao;
    private BlankFillingDao blankFillingDao;

    // READING DAOS
    private ReadingDao readingDao;

    private MultipleChoiceDao multipleChoiceDao;
    private GappedDao gappedDao;
    private MatchingExerciseDao matchingExerciseDao;


    // LIVE DATA PACKAGES
    private LiveData<List<UseOfEnglishPackage>> useOfEnglishLivePackages;

    private LiveData<List<ListeningPackage>> listeningPackages;

    private LiveData<List<ReadingPackage>> readingPackages;

    // INIT
    public QuestionRepository(Application application) {

        /**
         * CONSTRUCTOR
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

        listeningMultipleDao = questionDatabase.listeningMultipleDao();

        listeningOneDao = questionDatabase.listeningOneDao();

        blankFillingDao = questionDatabase.blankFillingDao();

        matchSpeakersDao = questionDatabase.matchSpeakersDao();


        readingDao = questionDatabase.readingDao();

        multipleChoiceDao = questionDatabase.multipleChoiceDao();

        gappedDao = questionDatabase.gappedDao();

        matchingExerciseDao = questionDatabase.matchingExerciseDao();


        /**
         * setting the live lists from the daos
         */
        useOfEnglishLivePackages = useOfEnglishDao.getAllUseOfEnglishPackages();

        listeningPackages = listeningDao.getAllListeningPackages();

        readingPackages = readingDao.getAllReadingPackages();

    }

    /**
     * TESTING THE LIVE DATA FOR A QUESTION
     */

    public LiveData<OpenClozeQuestion> getOpenClozeQuestionLiveData(int id) {

        return openClozeDao.getOpenCloze(id);

    }



    /**
     * methods to return the live data
     */


    public LiveData<List<UseOfEnglishPackage>> getAllUseOfEnglishPackages() {

        return useOfEnglishLivePackages;

    }

    public UseOfEnglishPackage getSingleUseOfEnglishPackages(int i) {

        return useOfEnglishDao.getSingleUseOfEnglishPackage(i);

    }

    public void updateUseOfEnglishPackage(UseOfEnglishPackage useOfEnglishPackage) {

        useOfEnglishDao.update(useOfEnglishPackage);

    }

    public LiveData<List<ListeningPackage>> getAllListeningPackages() {

        return listeningPackages;

    }

    public LiveData<ListeningPackage> getSingleListeningPackages(int i) {

        return listeningDao.getSingleListeningPackage(i);

    }

    public LiveData<List<ReadingPackage>> getAllReadingPackages() {

        return readingPackages;

    }

    public LiveData<ReadingPackage> getSingleReadingPackages(int i) {

        return readingDao.getSingleReadingPackage(i);

    }




    /**
     * RETURNING RX JAVA SINGLE OBJECTS TO POPULATE QUESTION SCREEN
     UOE SINGLES
     */

    public Single<OpenClozeQuestion> getMenuOpenCloze(int i ) {

        return openClozeDao.getModelParentOpenCloze(i);

    }

    public Single<KeywordTransformationQuestion> getMenuKeywordQuestion(int i ) {

        return keywordTransformationDao.getModelParentKeyword(i);

    }

    public Single<MultipleChoiceClozeQuestion> getMenuMultipleChoiceCloze(int i ) {

        return multipleChoiceClozeDao.getModelParentMultipleChoice(i);

    }

    public void updateMultipleChoiceCloze(MultipleChoiceClozeQuestion multipleChoiceClozeQuestion) {
        multipleChoiceClozeDao.updateMultipleChoiceClozeQuestion(multipleChoiceClozeQuestion);
    }

    public Single<WordFormationQuestion> getMenuWordFormation(int i ) {

        return wordFormationDao.getModelParentWordFormation(i);

    }

    public Single<MultipleChoiceQuestion> getMenuMulipleChoice(int i ) {

        return multipleChoiceDao.getModelParentMultipleChoice(i);

    }

    public Single<GappedTextQuestion> getMenuGappedText(int i ) {

        return gappedDao.getModelParentGappedText(i);

    }

    public Single<MatchingExerciseQuestion> getMenuMatchingExercise(int i ) {

        return matchingExerciseDao.getModelParentMatchingExercise(i);

    }

    public Single<ListeningMultipleSituationsQuestion> getMenuMulipleSituations(int i ) {

        return listeningMultipleDao.getModelParentListeningMultiple(i);

    }

    public Single<ListeningOneSituationQuestion> getMenuOneSituation(int i) {

        return listeningOneDao.getModelParentListeningOne(i);

    }

    public Single<BlankFillingQuestion> getMenuBlankFilling(int i ) {

        return blankFillingDao.getModelParentBlankFilling(i);

    }

    public Single<MatchSpeakersQuestion> getMenuMatchSpeakers(int i ) {

        return matchSpeakersDao.getModelParentMatchSpeakers(i);

    }


}
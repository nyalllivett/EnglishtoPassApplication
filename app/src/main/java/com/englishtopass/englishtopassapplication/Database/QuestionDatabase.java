package com.englishtopass.englishtopassapplication.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.englishtopass.englishtopassapplication.Converters.ListeningConverters.BlankFillingConverter;
import com.englishtopass.englishtopassapplication.Converters.ListeningConverters.ListeningMultipleSituationsConverter;
import com.englishtopass.englishtopassapplication.Converters.ListeningConverters.ListeningOneSituationConverter;
import com.englishtopass.englishtopassapplication.Converters.ListeningConverters.MatchSpeakersConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.ArrayListConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.BooleanListConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.KeywordTransformationConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.MultipleChoiceClozeConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.OpenClozeConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.QuestionPartConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.TestCompletionConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.WordFormationConverter;
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
import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningMultipleSituationsQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Package.ReadingPackage;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.GappedTextQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.MatchingExerciseQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.MultipleChoiceQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {UseOfEnglishPackage.class,
        ListeningPackage.class,
        ReadingPackage.class,
        MultipleChoiceClozeQuestion.class,
        OpenClozeQuestion.class,
        KeywordTransformationQuestion.class,
        WordFormationQuestion.class,
        MultipleChoiceQuestion.class,
        GappedTextQuestion.class,
        MatchingExerciseQuestion.class,
        ListeningMultipleSituationsQuestion.class,
        ListeningOneSituationQuestion.class,
        BlankFillingQuestion.class,
        MatchSpeakersQuestion.class


}, version = 1)

@TypeConverters({
        MultipleChoiceClozeConverter.class,
        OpenClozeConverter.class,
        KeywordTransformationConverter.class,
        WordFormationConverter.class,
        BlankFillingConverter.class,
        ListeningMultipleSituationsConverter.class,
        ListeningOneSituationConverter.class,
        MatchSpeakersConverter.class,
        TestCompletionConverter.class,
        ArrayListConverter.class,
        BooleanListConverter.class,
        QuestionPartConverter.class

})

public abstract class QuestionDatabase extends RoomDatabase {

    private static final String TAG = "QuestionDatabase";
    /**
     * DATABASE
     * defining the database
     */
    private static QuestionDatabase instance;

    /**
     * DAO
     * the methods that the database instance will call
     */

    abstract public MultipleChoiceClozeDao multipleChoiceClozeDao();

    abstract public UseOfEnglishDao useOfEnglishDao();

    abstract public ListeningDao listeningDao();

    abstract public OpenClozeDao openClozeDao();

    abstract public KeywordTransformationDao keywordTransformationDao();

    abstract public WordFormationDao wordFormationDao();

    abstract public ReadingDao readingDao();

    abstract public MultipleChoiceDao multipleChoiceDao();

    abstract public GappedDao gappedDao();

    abstract public MatchingExerciseDao matchingExerciseDao();

    abstract public ListeningMultipleDao listeningMultipleDao();

    abstract public ListeningOneDao listeningOneDao();

    abstract public BlankFillingDao blankFillingDao();

    abstract public MatchSpeakersDao matchSpeakersDao();


    /**
     * RETRIEVING DATABASE
     * returning a singleton instance of the database
     */
    public static synchronized QuestionDatabase getInstance(Context context){

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), QuestionDatabase.class, "question_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();

        }

        return instance;
    }


    /**
     * Callback to populate the database for testing purposes. will remove
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onCreate(db);
                    new PopulateDbAsync(instance).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UseOfEnglishDao mUoeDao;
        private final ListeningDao mListeningDao;
        private final ReadingDao readingDao;
        private final MultipleChoiceClozeDao multipleChoiceClozeDao;
        private final OpenClozeDao openClozeDao;
        private final KeywordTransformationDao keywordTransformationDao;
        private final WordFormationDao wordFormationDao;
        private final MultipleChoiceDao multipleChoiceDao;
        private final GappedDao gappedDao;
        private final MatchingExerciseDao matchingExerciseDao;
        private final ListeningMultipleDao listeningMultipleDao;
        private final ListeningOneDao listeningOneDao;
        private final BlankFillingDao blankFillingDao;
        private final MatchSpeakersDao matchSpeakersDao;

        PopulateDbAsync(QuestionDatabase db) {
            mUoeDao = db.useOfEnglishDao();
            mListeningDao = db.listeningDao();
            readingDao = db.readingDao();
            multipleChoiceClozeDao = db.multipleChoiceClozeDao();
            openClozeDao = db.openClozeDao();
            keywordTransformationDao = db.keywordTransformationDao();
            wordFormationDao = db.wordFormationDao();
            multipleChoiceDao = db.multipleChoiceDao();
            gappedDao = db.gappedDao();
            matchingExerciseDao = db.matchingExerciseDao();
            listeningMultipleDao = db.listeningMultipleDao();
            listeningOneDao = db.listeningOneDao();
            blankFillingDao = db.blankFillingDao();
            matchSpeakersDao = db.matchSpeakersDao();


        }

        @Override
        protected Void doInBackground(final Void... params) {

//            mUoeDao.deleteAll();

            String stringBody = "Charlotte Church looks like a normal teenager, but she is far from average. She has an amazing voice. Her fans stand in (1) ........ for hours to get tickets for her concerts and she is often on television.\n\nCharlotte’s singing (2) ........ began when she performed on a TV show at the age of 11. The head of a record company was so impressed by her voice that he(3) ........ her up on the spot.\n\nHer first album rose to number one in the charts. Charlotte still attends school in her home town when she can. (4) ........ , she is often away on tour for weeks at a time. She doesn’t miss out on lessons, though, because she takes her own tutor with her! She (5) ........ three hours every morning with him. Her exam results in all the (6) ........ she studies are impressive.\n\nBut how does she cope with this unusual way of life? She (7) ........ that she has the same friends as before. That may be true, but she can no longer go into town with them because everybody stops her in the street to ask for her (8) ........ .\n\nIt seems that, like most stars, she must learn to control these restrictions and the lack of privacy. It’s the price of fame!";

            UseOfEnglishPackage useOfEnglishPackage = new UseOfEnglishPackage(
                    "Who Wants to Be a Millionaire",
                    "The New Year",
                    "Greenland - The biggest Island in the World",
                    "Deep Sleep");

            long check = mUoeDao.insert(useOfEnglishPackage);

            multipleChoiceClozeDao.insert(new MultipleChoiceClozeQuestion("Who Wants to Be a Millionaire",
                    "These are test instructions",
                    (int)check,
                    stringBody, "one#two#three#four#five#six#seven#eight#nine#ten#eleven#twelve#thirteen#fourteen#" +
                    "fifteen#sixteen#seventeen#eighteen#ninteen#twenty" +
                    "#twentyone#twentytwo#twentythree#twentyfour#twentyfive#" +
                    "twentysix#twentyseven#twentyeight#twentynine#thirty#thirtyone#thirtytwo",
                    "one#five#nine#thirteen#seventeen#twentyone#twentyfive#twentynine"
                    ));

            openClozeDao.insert(new OpenClozeQuestion(
                    "The New Year",
                    "These are test instructions",
                    (int)check,
                    stringBody,
                    "one#five#nine#thirteen#seventeen#twentyone#twentyfive#twentynine"
                    ));

            keywordTransformationDao.insert(new KeywordTransformationQuestion(
                    "Greenland - The biggest Island in the World",
                    "These are test instructions",
                    (int)check,
                    stringBody,
                    "one#five#nine#thirteen#seventeen#twentyone#twentyfive#twentynine",
                    "product#ill#effect#science#add#press#advantage#spice"
                    ));

            wordFormationDao.insert(new WordFormationQuestion(
                    "Deep Sleep",
                    "These are test instructions",
                    (int)check,
                    stringBody));


            UseOfEnglishPackage useOfEnglishPackage2 = new UseOfEnglishPackage(

                    "The Story of Adidas of Puma",
                    "The Bermuda Triangle",
                    "Walt Disney",
                    "Technology and the Young"

            );

            long check2 = mUoeDao.insert(useOfEnglishPackage2);

            multipleChoiceClozeDao.insert(new MultipleChoiceClozeQuestion(
                    "The Story of Adidas of Puma",
                    "These are test instructions",
                    (int) check2,
                    stringBody,
                    "one#two#three#four#five#six#seven#eight#nine#ten#eleven#twelve#thirteen#fourteen#" +
                    "fifteen#sixteen#seventeen#eighteen#ninteen#twenty" +
                    "#twentyone#twentytwo#twentythree#twentyfour#twentyfive#" +
                    "twentysix#twentyseven#twentyeight#twentynine#thirty#thirtyone#thirtytwo",
                    "one#five#nine#thirteen#seventeen#twentyone#twentyfive#twentynine"));

            openClozeDao.insert(new OpenClozeQuestion(
                    "The Bermuda Triangle",
                    "These are test instructions",
                    (int) check2,
                    stringBody,
                    "one#five#nine#thirteen#seventeen#twentyone#twentyfive#twentynine"));

            keywordTransformationDao.insert(new KeywordTransformationQuestion(
                    "Greenland - The biggest Island in the World",
                    "These are test instructions",
                    (int)check2,
                    stringBody,
                    "one#five#nine#thirteen#seventeen#twentyone#twentyfive#twentynine",
                    "product#ill#effect#science#add#press#advantage#spice"
            ));

            wordFormationDao.insert(new WordFormationQuestion(
                    "Technology and the Young",
                    "These are test instructions",
                    (int) check2,
                    stringBody));




            ListeningPackage listeningPackage = new ListeningPackage(
                    "Who Wants to Be a Millionaire",
                    "The New Year",
                    "Greenland - The biggest Island in the World",
                    "Deep Sleep");

            long check3 = mListeningDao.insert(listeningPackage);

            listeningMultipleDao.insert(new ListeningMultipleSituationsQuestion(
                    "Who Wants to Be a Millionaire",
                    "These are test instructions",
                    (int)check3,
                    stringBody));

            listeningOneDao.insert(new ListeningOneSituationQuestion(
                    "The New Year",
                    "These are test instructions",
                    (int)check3,
                    stringBody));

            blankFillingDao.insert(new BlankFillingQuestion(
                    "Greenland - The biggest Island in the World",
                    "These are test instructions",
                    (int) check3,
                    stringBody));

            matchSpeakersDao.insert(new MatchSpeakersQuestion(
                    "Deep Sleep",
                    "These are test instructions",
                    (int)check3,
                    stringBody));


            ListeningPackage listeningPackage1 = new ListeningPackage(

                    "The Story of Adidas of Puma",
                    "The Bermuda Triangle",
                    "Walt Disney",
                    "Technology and the Young"

            );

            long check4 = mListeningDao.insert(listeningPackage1);

            listeningMultipleDao.insert(new ListeningMultipleSituationsQuestion(
                    "The Story of Adidas of Puma",
                    "These are test instructions",
                    (int) check4,
                    stringBody));

            listeningOneDao.insert(new ListeningOneSituationQuestion(
                    "The Bermuda Triangle",
                    "These are test instructions",
                    (int) check4,
                    stringBody));

            blankFillingDao.insert(new BlankFillingQuestion(
                    "Walt Disney",
                    "These are test instructions",
                    (int) check4,
                    stringBody));

            matchSpeakersDao.insert(new MatchSpeakersQuestion(
                    "Technology and the Young",
                    "These are test instructions",
                    (int) check4,
                    stringBody));



            ReadingPackage readingPackage = new ReadingPackage(
                    "Who Wants to Be a Millionaire",
                    "The New Year",
                    "Greenland - The biggest Island in the World"
            );

            long check5 = readingDao.insert(readingPackage);

            multipleChoiceDao.insert(new MultipleChoiceQuestion("Who Wants to Be a Millionaire", (int)check5, stringBody));

            gappedDao.insert(new GappedTextQuestion( "The New Year", (int)check5, stringBody));

            matchingExerciseDao.insert(new MatchingExerciseQuestion("Greenland - The biggest Island in the World",  (int) check5, stringBody));

            ReadingPackage readingPackage1 = new ReadingPackage(

                    "The Story of Adidas of Puma",
                    "The Bermuda Triangle",
                    "Walt Disney"

            );

            long check6 = readingDao.insert(readingPackage1);

            multipleChoiceDao.insert(new MultipleChoiceQuestion("The Story of Adidas of Puma",(int) check6, stringBody));

            gappedDao.insert(new GappedTextQuestion("The Bermuda Triangle",(int) check6, stringBody));

            matchingExerciseDao.insert(new MatchingExerciseQuestion("Walt Disney",(int) check6, stringBody));

            return null;

        }
    }

}

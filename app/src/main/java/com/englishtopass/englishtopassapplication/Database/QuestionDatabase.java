package com.englishtopass.englishtopassapplication.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.englishtopass.englishtopassapplication.Converters.ListeningConverters.BlankFillingConverter;
import com.englishtopass.englishtopassapplication.Converters.ListeningConverters.ListeningMultipleSituationsConverter;
import com.englishtopass.englishtopassapplication.Converters.ListeningConverters.ListeningOneSituationConverter;
import com.englishtopass.englishtopassapplication.Converters.ListeningConverters.MatchSpeakersConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.KeywordTransformationConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.MultipleChoiceClozeConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.OpenClozeConverter;
import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.WordFormationConverter;
import com.englishtopass.englishtopassapplication.Dao.ListeningDao;
import com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDao;
import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningMultipleSituationsQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;
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

@Database(entities = {UseOfEnglishPackage.class, ListeningPackage.class}, version = 1)

@TypeConverters({
        MultipleChoiceClozeConverter.class,
        OpenClozeConverter.class,
        KeywordTransformationConverter.class,
        WordFormationConverter.class,
        BlankFillingConverter.class,
        ListeningMultipleSituationsConverter.class,
        ListeningOneSituationConverter.class,
        MatchSpeakersConverter.class

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

    abstract public UseOfEnglishDao useOfEnglishDao();

    abstract public ListeningDao listeningDao();

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
                public void onCreate (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(instance).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UseOfEnglishDao mUoeDao;
        private final ListeningDao mListeningDao;

        PopulateDbAsync(QuestionDatabase db) {
            mUoeDao = db.useOfEnglishDao();
            mListeningDao = db.listeningDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

//            mUoeDao.deleteAll();

            Log.d(TAG, "doInBackground: im populating");

            UseOfEnglishPackage useOfEnglishPackage = new UseOfEnglishPackage(


                    new MultipleChoiceClozeQuestion("Who Wants to Be a Millionaire", "body"),

                    new OpenClozeQuestion("The New Year", "body"),

                    new KeywordTransformationQuestion("Greenland - The biggest Island in the World", "body"),

                    new WordFormationQuestion("Deep Sleep", "body")

            );

            mUoeDao.insert(useOfEnglishPackage);

            UseOfEnglishPackage useOfEnglishPackage2 = new UseOfEnglishPackage(


                    new MultipleChoiceClozeQuestion("The Story of Adidas of Puma", "body"),

                    new OpenClozeQuestion("The Bermuda Triangle", "body"),

                    new KeywordTransformationQuestion("Walt Disney", "body"),

                    new WordFormationQuestion("Technology and the Young", "body")

            );

            mUoeDao.insert(useOfEnglishPackage2);

            ListeningPackage listeningPackage = new ListeningPackage(


                    new ListeningMultipleSituationsQuestion("Only fools and Horses", "body"),

                    new BlankFillingQuestion("The Summer and Winter", "body"),

                    new MatchSpeakersQuestion("England - The best Island in the World", "body"),

                    new ListeningOneSituationQuestion("Deep Water", "body")

            );

            mListeningDao.insert(listeningPackage);

            ListeningPackage listeningPackage2 = new ListeningPackage(


                    new ListeningMultipleSituationsQuestion("Levis, The Original Jeans Brand", "body"),

                    new BlankFillingQuestion("Trigonometry", "body"),

                    new MatchSpeakersQuestion("Steven Speilberg", "body"),

                    new ListeningOneSituationQuestion("Care Homes", "body")

            );

            mListeningDao.insert(listeningPackage2);

            return null;
        }
    }

}

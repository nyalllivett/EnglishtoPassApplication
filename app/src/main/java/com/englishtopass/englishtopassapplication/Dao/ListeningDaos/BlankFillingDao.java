package com.englishtopass.englishtopassapplication.Dao.ListeningDaos;

import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface BlankFillingDao {

    @Query("SELECT * FROM blank_filling_table")
    LiveData<List<BlankFillingQuestion>> getAllBlankFillingQuestions();

    @Query("SELECT * FROM blank_filling_table")
    LiveData<BlankFillingQuestion> getBlankFillingQuestion();

    @Query("SELECT COUNT(*) FROM blank_filling_table")
    int count();

    @Insert
    void insert(BlankFillingQuestion blankFillingQuestion);

    @Query("SELECT * FROM blank_filling_table WHERE :id = listening_id")
    Single<BlankFillingQuestion> getModelParentBlankFilling(int id);

}

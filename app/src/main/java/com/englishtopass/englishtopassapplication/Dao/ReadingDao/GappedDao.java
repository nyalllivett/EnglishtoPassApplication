package com.englishtopass.englishtopassapplication.Dao.ReadingDao;

import com.englishtopass.englishtopassapplication.Model.Reading.Questions.GappedTextQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface GappedDao {

    @Query("SELECT * FROM gapped_text_table")
    LiveData<List<GappedTextQuestion>> getAllGappedTextQuestions();

    @Query("SELECT * FROM gapped_text_table")
    LiveData<GappedTextQuestion> getGappedTextQuestion();

    @Query("SELECT COUNT(*) FROM gapped_text_table")
    int count();

    @Insert
    void insert(GappedTextQuestion gappedTextQuestion);

    @Query("SELECT * FROM gapped_text_table WHERE :id = reading_id")
    Single<GappedTextQuestion> getModelParentGappedText(int id);

}

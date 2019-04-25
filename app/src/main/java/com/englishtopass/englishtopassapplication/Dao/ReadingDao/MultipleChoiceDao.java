package com.englishtopass.englishtopassapplication.Dao.ReadingDao;

import com.englishtopass.englishtopassapplication.Model.Reading.Questions.MultipleChoiceQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface MultipleChoiceDao {

    @Query("SELECT * FROM multiple_choice_table")
    LiveData<List<MultipleChoiceQuestion>> getAllMultipleChoiceQuestions();

    @Query("SELECT * FROM multiple_choice_table")
    LiveData<MultipleChoiceQuestion> getMultipleChoiceQuestion();

    @Query("SELECT COUNT(*) FROM multiple_choice_table")
    int count();

    @Insert
    void insert(MultipleChoiceQuestion multipleChoiceQuestion);


    @Query("SELECT * FROM multiple_choice_table WHERE :id = reading_id")
    Single<MultipleChoiceQuestion> getModelParentMultipleChoice(int id);


}

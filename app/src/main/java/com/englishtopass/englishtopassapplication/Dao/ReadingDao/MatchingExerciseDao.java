package com.englishtopass.englishtopassapplication.Dao.ReadingDao;

import com.englishtopass.englishtopassapplication.Model.Reading.Questions.MatchingExerciseQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface MatchingExerciseDao {

    @Query("SELECT * FROM matching_exercise_table")
    LiveData<List<MatchingExerciseQuestion>> getAllMatchingExerciseQuestions();

    @Query("SELECT * FROM matching_exercise_table")
    LiveData<MatchingExerciseQuestion> getMatchingExerciseQuestion();

    @Query("SELECT COUNT(*) FROM matching_exercise_table")
    int count();

    @Insert
    void insert(MatchingExerciseQuestion matchingExerciseQuestion);


    @Query("SELECT * FROM matching_exercise_table WHERE :id = reading_id")
    Single<MatchingExerciseQuestion> getModelParentMatchingExercise(int id);


}

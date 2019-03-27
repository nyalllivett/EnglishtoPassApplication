package com.englishtopass.englishtopassapplication.Dao.ListeningDaos;

import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningMultipleSituationsQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface ListeningMultipleDao {

    @Query("SELECT * FROM listening_multiple_situations_table")
    LiveData<List<ListeningMultipleSituationsQuestion>> getAllMultipleListeningQuestions();

    @Query("SELECT * FROM listening_multiple_situations_table")
    LiveData<ListeningMultipleSituationsQuestion> getMultipleListeningQuestion();

    @Query("SELECT COUNT(*) FROM listening_multiple_situations_table")
    int count();

    @Insert
    void insert(ListeningMultipleSituationsQuestion listeningMultipleSituationsQuestion);

    @Query("SELECT * FROM listening_multiple_situations_table WHERE :id = listening_id")
    Single<ListeningMultipleSituationsQuestion> getModelParentListeningMultiple(int id);
}

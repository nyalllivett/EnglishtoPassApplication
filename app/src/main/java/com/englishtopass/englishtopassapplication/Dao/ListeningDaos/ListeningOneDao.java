package com.englishtopass.englishtopassapplication.Dao.ListeningDaos;

import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface ListeningOneDao {

    @Query("SELECT * FROM listening_one_situation_table")
    LiveData<List<ListeningOneSituationQuestion>> getAllOneListeningQuestions();

    @Query("SELECT * FROM listening_one_situation_table")
    LiveData<ListeningOneSituationQuestion> getOneListeningQuestion();

    @Query("SELECT COUNT(*) FROM listening_one_situation_table")
    int count();

    @Insert
    void insert(ListeningOneSituationQuestion listeningOneSituationQuestion);

    @Query("SELECT * FROM listening_one_situation_table WHERE :id = listening_id")
    Single<ListeningOneSituationQuestion> getModelParentListeningOne(int id);
}

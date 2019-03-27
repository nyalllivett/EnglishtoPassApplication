package com.englishtopass.englishtopassapplication.Dao.ListeningDaos;

import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface MatchSpeakersDao {

    @Query("SELECT * FROM match_speakers_table")
    LiveData<List<MatchSpeakersQuestion>> getAllMatchSpeakersQuestion();

    @Query("SELECT * FROM match_speakers_table")
    LiveData<MatchSpeakersQuestion> getMatchSpeakersQuestion();

    @Query("SELECT COUNT(*) FROM match_speakers_table")
    int count();

    @Insert
    void insert(MatchSpeakersQuestion matchSpeakersQuestion);

    @Query("SELECT * FROM match_speakers_table WHERE :id = listening_id")
    Single<MatchSpeakersQuestion> getModelParentMatchSpeakers(int id);

}

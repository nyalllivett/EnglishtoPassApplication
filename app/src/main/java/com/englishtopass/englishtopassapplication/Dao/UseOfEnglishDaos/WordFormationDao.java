package com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDaos;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface WordFormationDao {


    @Insert
    void insert(WordFormationQuestion wordFormationQuestion);

    @Query("SELECT * FROM word_formation_table WHERE :id = uoe_id")
    Single<WordFormationQuestion> getModelParentWordFormation(int id);


}

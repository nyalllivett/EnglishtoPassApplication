package com.englishtopass.englishtopassapplication.Dao;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;
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

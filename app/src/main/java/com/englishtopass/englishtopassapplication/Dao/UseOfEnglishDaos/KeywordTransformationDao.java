package com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDaos;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface KeywordTransformationDao {


    @Insert
    void insert(KeywordTransformationQuestion keywordTransformationQuestion);

    @Query("SELECT * FROM keyword_transformations WHERE :id = uoe_id")
    Single<KeywordTransformationQuestion> getModelParentKeyword(int id);

    @Query("SELECT * FROM keyword_transformations WHERE :id = uoe_id")
    LiveData<KeywordTransformationQuestion> getKeywordTransformation(int id);

}

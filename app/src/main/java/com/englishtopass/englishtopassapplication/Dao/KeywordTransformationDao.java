package com.englishtopass.englishtopassapplication.Dao;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;

import java.security.Key;

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


}

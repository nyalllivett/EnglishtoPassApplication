package com.englishtopass.englishtopassapplication.Dao;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface OpenClozeDao {


    @Insert
    void insert(OpenClozeQuestion openClozeQuestion);

    @Query("SELECT * FROM open_cloze_table WHERE :id = uoe_id")
    Single<OpenClozeQuestion> getModelParentOpenCloze(int id);


}

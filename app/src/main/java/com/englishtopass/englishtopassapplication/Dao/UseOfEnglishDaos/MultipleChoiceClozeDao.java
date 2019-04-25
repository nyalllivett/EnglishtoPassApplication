package com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDaos;

import com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters.BooleanListConverter;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.Update;
import io.reactivex.Single;

@Dao
public interface MultipleChoiceClozeDao {


    @Insert
    void insert(MultipleChoiceClozeQuestion multipleChoiceClozeQuestion);

    @Query("SELECT * FROM multiple_choice_cloze_table WHERE :id = uoe_id")
    Single<MultipleChoiceClozeQuestion> getModelParentMultipleChoice(int id);

    @Update
    void updateMultipleChoiceClozeQuestion(MultipleChoiceClozeQuestion multipleChoiceClozeQuestion);


}

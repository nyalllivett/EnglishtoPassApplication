package com.englishtopass.englishtopassapplication.Dao.UseOfEnglishDaos;

import com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;


@Dao
public interface UseOfEnglishDao {

    @Query("SELECT * FROM use_of_english_questions_table")
    LiveData<List<UseOfEnglishPackage>> getAllUseOfEnglishPackages();

    @Query("SELECT * FROM use_of_english_questions_table WHERE id = :id_entry")
    Single<UseOfEnglishPackage> getSingleUseOfEnglishPackage(int id_entry);

    @Query("SELECT COUNT(*) FROM use_of_english_questions_table")
    int count();

    @Insert
    long insert(UseOfEnglishPackage useOfEnglishPackage);

    @Update
    void update(UseOfEnglishPackage useOfEnglishPackage);

    @Delete
    void delete(UseOfEnglishPackage useOfEnglishPackage);

}

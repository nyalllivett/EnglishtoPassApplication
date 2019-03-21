package com.englishtopass.englishtopassapplication.Dao;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UseOfEnglishDao {

    @Query("SELECT * FROM use_of_english_questions_table")
    LiveData<List<UseOfEnglishPackage>> getAllUseOfEnglishPackages();

    @Query("SELECT * FROM use_of_english_questions_table WHERE id = :id_entry")
    LiveData<UseOfEnglishPackage> getSingleUseOfEnglishPackage(int id_entry);
//
//    @Query("UPDATE use_of_english_questions_table SET u WHERE id = :id_entry")
//    LiveData<UseOfEnglishPackage> getSingleUseOfEnglishPackage(int id_entry);

    @Query("SELECT COUNT(*) FROM use_of_english_questions_table")
    int count();

    @Insert
    void insert(UseOfEnglishPackage useOfEnglishPackage);

    @Insert
    void insertAll(UseOfEnglishPackage... useOfEnglishPackage);

    @Delete
    void delete(UseOfEnglishPackage useOfEnglishPackage);

    @Query("DELETE FROM use_of_english_questions_table")
    void deleteAll();


}

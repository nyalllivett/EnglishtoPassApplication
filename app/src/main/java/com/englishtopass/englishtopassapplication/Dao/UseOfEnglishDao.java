package com.englishtopass.englishtopassapplication.Dao;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UseOfEnglishDao {

    @Query("SELECT * FROM use_of_english_questions_table")
    LiveData<List<UseOfEnglishPackage>> getAllUseOfEnglishPackages();

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

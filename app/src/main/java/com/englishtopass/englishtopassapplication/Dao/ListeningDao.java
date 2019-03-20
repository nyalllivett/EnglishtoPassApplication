package com.englishtopass.englishtopassapplication.Dao;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ListeningDao {

    @Query("SELECT * FROM listening_questions_table")
    LiveData<List<ListeningPackage>> getAllListeningPackages();

    @Query("SELECT * FROM listening_questions_table WHERE id = :id_entry")
    LiveData<ListeningPackage> getSingleListeningPackage(int id_entry);

    @Query("SELECT COUNT(*) FROM listening_questions_table")
    int count();

    @Insert
    void insert(ListeningPackage listeningPackage);

    @Insert
    void insertAll(ListeningPackage... listeningPackage);

    @Delete
    void delete(ListeningPackage listeningPackage);

    @Query("DELETE FROM listening_questions_table")
    void deleteAll();

}

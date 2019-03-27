package com.englishtopass.englishtopassapplication.Dao.ListeningDaos;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ListeningDao {

    @Query("SELECT * FROM listening_package_table")
    LiveData<List<ListeningPackage>> getAllListeningPackages();

    @Query("SELECT * FROM listening_package_table WHERE id = :id_entry")
    LiveData<ListeningPackage> getSingleListeningPackage(int id_entry);

    @Query("SELECT COUNT(*) FROM listening_package_table")
    int count();

    @Insert
    long insert(ListeningPackage listeningPackage);

    @Insert
    void insertAll(ListeningPackage... listeningPackage);

    @Delete
    void delete(ListeningPackage listeningPackage);

    @Query("DELETE FROM listening_package_table")
    void deleteAll();

}

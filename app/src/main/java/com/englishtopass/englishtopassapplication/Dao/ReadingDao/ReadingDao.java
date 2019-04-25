package com.englishtopass.englishtopassapplication.Dao.ReadingDao;

import com.englishtopass.englishtopassapplication.Model.Reading.Package.ReadingPackage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ReadingDao {

    @Query("SELECT * FROM reading_package_table")
    LiveData<List<ReadingPackage>> getAllReadingPackages();

    @Query("SELECT * FROM reading_package_table WHERE id = :id_entry")
    LiveData<ReadingPackage> getSingleReadingPackage(int id_entry);

    @Query("SELECT COUNT(*) FROM reading_package_table")
    int count();

    @Insert
    long insert(ReadingPackage readingPackage);

    @Insert
    void insertAll(ReadingPackage... readingPackage);

    @Delete
    void delete(ReadingPackage readingPackage);

    @Query("DELETE FROM reading_package_table")
    void deleteAll();

}

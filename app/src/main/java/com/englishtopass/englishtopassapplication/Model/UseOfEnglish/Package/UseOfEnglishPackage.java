package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "use_of_english_questions_table")
public class UseOfEnglishPackage {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    private UoeCompletion uoeCompletion;

    public UseOfEnglishPackage() {

        this.uoeCompletion = UoeCompletion.FIRST_COMPLETE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UoeCompletion getUoeCompletion() {
        return uoeCompletion;
    }

    public void setUoeCompletion(UoeCompletion uoeCompletion) {
        this.uoeCompletion = uoeCompletion;
    }

}

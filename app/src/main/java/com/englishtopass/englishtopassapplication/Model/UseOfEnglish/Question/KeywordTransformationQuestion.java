package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;


import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "keyword_transformations", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = UseOfEnglishPackage.class, parentColumns = "id", childColumns = "uoe_id"))
public class KeywordTransformationQuestion extends UoeParent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "uoe_id", index = true)
    private final int uoeId;

    @NonNull
    private String body;

    public KeywordTransformationQuestion(@NonNull String title, int uoeId, @NonNull String body) {
        super(title);
        this.uoeId = uoeId;
        this.body = body;
        this.testTimeElapsed = 0L;

    }

    @NonNull
    public String getBody() {
        return body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUoeId() {
        return uoeId;
    }
}

package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;


import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "keyword_transformations", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = UseOfEnglishPackage.class, parentColumns = "id", childColumns = "uoeId"))
public class KeywordTransformationQuestion extends ModelUoeParent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(index = true)
    private final int uoeId;

    @NonNull
    private String questionBody;

    private boolean complete;


    public KeywordTransformationQuestion(@NonNull String title, int uoeId, @NonNull String questionBody) {
        super(title);
        this.uoeId = uoeId;
        this.questionBody = questionBody;
        this.timeElapsed = 0;

    }

    @NonNull
    public String getQuestionTitle() {
        return title;
    }

    @NonNull
    public String getQuestionBody() {
        return questionBody;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
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

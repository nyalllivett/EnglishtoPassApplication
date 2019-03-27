package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_formation_table", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = UseOfEnglishPackage.class, parentColumns = "id", childColumns = "uoe_id"))
public class WordFormationQuestion extends UoeParent {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "uoe_id", index = true)
    private final int uoeId;

    @NonNull
    private String questionBody;

    private boolean complete;

    public WordFormationQuestion(@NonNull String title, int uoeId, @NonNull String questionBody) {
        super(title);
        this.uoeId = uoeId;
        this.questionBody = questionBody;
        this.complete = false;
        this.testTimeElapsed = 0;

    }

    @NonNull
    public String getQuestionBody() {
        return questionBody;
    }

    public boolean isComplete() {
        return complete;
    }



    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getUoeId() {
        return uoeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

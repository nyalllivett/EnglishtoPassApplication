package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;


import com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe.*;

@Entity(tableName = "keyword_transformations", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = UseOfEnglishPackage.class, parentColumns = "id", childColumns = "uoe_id"))
public class KeywordTransformationQuestion extends UoeParent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "uoe_id", index = true)
    private final int uoeId;

    @NonNull
    private String body;

    private String correctAnswers;
    private String keywords;

    /**
     * parent handles
     * title
     * question type
     * time elapsed
     */
    public KeywordTransformationQuestion(String title, String instructions, int uoeId, @NonNull String body, String correctAnswers, String keywords) {
        super(title, instructions, "Keyword Transformation", new boolean[]{ false, false, false, false, false, false, false, false});
        this.uoeId = uoeId;
        this.body = body;
        this.correctAnswers = correctAnswers;
        this.keywords = keywords;
    }

    @NonNull
    public String getBody() {
        return body;
    }

    public void setBody(@NonNull String body) {
        this.body = body;
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

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

}

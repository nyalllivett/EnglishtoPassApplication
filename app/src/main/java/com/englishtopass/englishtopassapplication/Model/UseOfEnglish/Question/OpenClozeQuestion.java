package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;

import com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe.KEYWORD_TRANSFORMATION;
import static com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe.OPEN_CLOZE;

@Entity(tableName = "open_cloze_table", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = UseOfEnglishPackage.class, parentColumns = "id", childColumns = "uoe_id"))
public class OpenClozeQuestion extends UoeParent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "uoe_id", index = true)
    private int uoeId;

    @NonNull
    private String body;
    private String correctAnswers;
    private boolean[] answersAreCorrect;

    /**
     * parent handles
     * title
     * question type
     * time elapsed
     */
    public OpenClozeQuestion(@NonNull String title, int uoeId, @NonNull String body, String correctAnswers) {
        super(title, OPEN_CLOZE);
        this.uoeId = uoeId;
        this.body = body;
        this.correctAnswers = correctAnswers;
        this.answersAreCorrect = new boolean[]{false, false, false, false, false, false, false, false};

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

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }


    public boolean[] getAnswersAreCorrect() {
        return answersAreCorrect;
    }

    public void setAnswersAreCorrect(boolean[] answersAreCorrect) {
        this.answersAreCorrect = answersAreCorrect;
    }

}

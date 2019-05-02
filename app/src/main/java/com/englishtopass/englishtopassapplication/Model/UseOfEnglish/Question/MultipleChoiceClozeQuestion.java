package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;

import com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe.MULTIPLE_CHOICE_CLOZE;

@Entity(tableName = "multiple_choice_cloze_table", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = UseOfEnglishPackage.class, parentColumns = "id", childColumns = "uoe_id"))
public class MultipleChoiceClozeQuestion extends UoeParent {

    // Id
    @PrimaryKey(autoGenerate = true)
    private int id;

    // Primary key for UOE package
    @ColumnInfo(name = "uoe_id", index = true)
    private int uoeId;

    private String body;
    private String allAnswers;
    private String correctAnswers;
    private boolean[] answersAreCorrect;

    /**
     * parent handles
     * title
     * question type
     * time elapsed
     */
    public MultipleChoiceClozeQuestion(@NonNull String title, int uoeId, @NonNull String body,@NonNull String allAnswers, @NonNull String correctAnswers) {
        super(title, MULTIPLE_CHOICE_CLOZE);
        this.uoeId = uoeId;
        this.body = body;
        this.allAnswers = allAnswers;
        this.correctAnswers = correctAnswers;
        this.answersAreCorrect = new boolean[]{false, false, false, false, false, false, false, false};

    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
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

    public String getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(String allAnswers) {
        this.allAnswers = allAnswers;
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

    public void finishQuestion(long timeElapsed, boolean completed, boolean[] markedAnswers) {

        this.setTestTimeElapsed(timeElapsed);
        this.setComplete(complete);
        this.setAnswersAreCorrect(markedAnswers);


    }

}

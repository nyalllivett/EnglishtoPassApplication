package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package;

import com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "use_of_english_questions_table")
public class UseOfEnglishPackage {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String multipleChoiceClozeTitle;
    private String openClozeTitle;
    private String keywordTransformationTitle;
    private String wordFormationTitle;

    private long testTimeElapsed;

    private QuestionPartUoe questionPartUoe;

    public UseOfEnglishPackage(String multipleChoiceClozeTitle, String openClozeTitle,
                               String keywordTransformationTitle, String wordFormationTitle) {

        this.multipleChoiceClozeTitle = multipleChoiceClozeTitle;
        this.openClozeTitle = openClozeTitle;
        this.keywordTransformationTitle = keywordTransformationTitle;
        this.wordFormationTitle = wordFormationTitle;
        this.testTimeElapsed = 0L;
        this.questionPartUoe = QuestionPartUoe.MULTIPLE_CHOICE_CLOZE;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionPartUoe getQuestionPartUoe() {
        return questionPartUoe;
    }

    public void setQuestionPartUoe(QuestionPartUoe questionPartUoe) {
        this.questionPartUoe = questionPartUoe;
    }

    public String getMultipleChoiceClozeTitle() {
        return multipleChoiceClozeTitle;
    }

    public void setMultipleChoiceClozeTitle(String multipleChoiceClozeTitle) {
        this.multipleChoiceClozeTitle = multipleChoiceClozeTitle;
    }

    public String getOpenClozeTitle() {
        return openClozeTitle;
    }

    public void setOpenClozeTitle(String openClozeTitle) {
        this.openClozeTitle = openClozeTitle;
    }

    public String getKeywordTransformationTitle() {
        return keywordTransformationTitle;
    }

    public void setKeywordTransformationTitle(String keywordTransformationTitle) {
        this.keywordTransformationTitle = keywordTransformationTitle;
    }

    public String getWordFormationTitle() {
        return wordFormationTitle;
    }

    public void setWordFormationTitle(String wordFormationTitle) {
        this.wordFormationTitle = wordFormationTitle;
    }

    public long getTestTimeElapsed() {
        return testTimeElapsed;
    }

    public void setTestTimeElapsed(long testTimeElapsed) {
        this.testTimeElapsed = testTimeElapsed;
    }
}

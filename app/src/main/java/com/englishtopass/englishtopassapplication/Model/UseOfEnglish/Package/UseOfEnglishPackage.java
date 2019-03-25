package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "use_of_english_questions_table")
public class UseOfEnglishPackage {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    private String multipleChoiceClozeTitle;
    private String openClozeTitle;
    private String keywordTransformationTitle;
    private String wordFormationTitle;

    private long uoeTimeElapsed;

    private UoeCompletion uoeCompletion;

    public UseOfEnglishPackage(String multipleChoiceClozeTitle, String openClozeTitle,
                               String keywordTransformationTitle, String wordFormationTitle) {

        this.multipleChoiceClozeTitle = multipleChoiceClozeTitle;
        this.openClozeTitle = openClozeTitle;
        this.keywordTransformationTitle = keywordTransformationTitle;
        this.wordFormationTitle = wordFormationTitle;
        this.uoeTimeElapsed = 0L;
        this.uoeCompletion = UoeCompletion.NOT_STARTED;

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

    public long getUoeTimeElapsed() {
        return uoeTimeElapsed;
    }

    public void setUoeTimeElapsed(long uoeTimeElapsed) {
        this.uoeTimeElapsed = uoeTimeElapsed;
    }
}

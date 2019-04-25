package com.englishtopass.englishtopassapplication.Model.Reading.Package;

import com.englishtopass.englishtopassapplication.Enums.TestCompletion;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reading_package_table")
public class ReadingPackage {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String multipleChoiceTitle;
    private String gappedTextTitle;
    private String matchingExerciseTitle;

    private long testTimeElapsed;

    private TestCompletion testCompletion;

    public ReadingPackage(String multipleChoiceTitle, String gappedTextTitle, String matchingExerciseTitle) {
        this.multipleChoiceTitle = multipleChoiceTitle;
        this.gappedTextTitle = gappedTextTitle;
        this.matchingExerciseTitle = matchingExerciseTitle;
        this.testTimeElapsed = 0L;
        this.testCompletion = TestCompletion.NOT_STARTED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMultipleChoiceTitle() {
        return multipleChoiceTitle;
    }

    public void setMultipleChoiceTitle(String multipleChoiceTitle) {
        this.multipleChoiceTitle = multipleChoiceTitle;
    }

    public String getGappedTextTitle() {
        return gappedTextTitle;
    }

    public void setGappedTextTitle(String gappedTextTitle) {
        this.gappedTextTitle = gappedTextTitle;
    }

    public String getMatchingExerciseTitle() {
        return matchingExerciseTitle;
    }

    public void setMatchingExerciseTitle(String matchingExerciseTitle) {
        this.matchingExerciseTitle = matchingExerciseTitle;
    }

    public long getTestTimeElapsed() {
        return testTimeElapsed;
    }

    public void setTestTimeElapsed(long testTimeElapsed) {
        this.testTimeElapsed = testTimeElapsed;
    }

    public TestCompletion getTestCompletion() {
        return testCompletion;
    }

    public void setTestCompletion(TestCompletion testCompletion) {
        this.testCompletion = testCompletion;
    }
}

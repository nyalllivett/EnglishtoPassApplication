package com.englishtopass.englishtopassapplication.Model.Reading.Questions.Parent;

public class ReadingParent {

    protected String title;
    protected int testTimeElapsed;

    public ReadingParent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTestTimeElapsed() {
        return testTimeElapsed;
    }

    public void setTestTimeElapsed(int testTimeElapsed) {
        this.testTimeElapsed = testTimeElapsed;
    }

}

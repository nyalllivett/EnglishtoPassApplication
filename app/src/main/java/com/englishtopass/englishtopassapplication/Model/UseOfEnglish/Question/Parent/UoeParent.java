package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent;

public class UoeParent {

    protected String title;
    protected int testTimeElapsed;

    public UoeParent(String title) {
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

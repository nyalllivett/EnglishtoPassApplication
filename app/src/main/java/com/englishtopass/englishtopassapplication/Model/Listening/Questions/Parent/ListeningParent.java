package com.englishtopass.englishtopassapplication.Model.Listening.Questions.Parent;

public class ListeningParent {

    protected String title;
    protected int testTimeElapsed;

    public ListeningParent(String title) {
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

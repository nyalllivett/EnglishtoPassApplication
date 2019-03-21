package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent;

public class ModelUoeParent {


    protected String title;
    protected int timeElapsed;

    public ModelUoeParent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}

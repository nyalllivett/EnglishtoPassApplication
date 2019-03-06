package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;

import androidx.annotation.NonNull;

public class OpenClozeQuestion {


    @NonNull
    private String questionTitle;

    @NonNull
    private String questionBody;

    private boolean complete;

    public OpenClozeQuestion(@NonNull String questionTitle, @NonNull String questionBody) {
        this.questionTitle = questionTitle;
        this.questionBody = questionBody;
        this.complete = false;
    }

    @NonNull
    public String getQuestionTitle() {
        return questionTitle;
    }

    @NonNull
    public String getQuestionBody() {
        return questionBody;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

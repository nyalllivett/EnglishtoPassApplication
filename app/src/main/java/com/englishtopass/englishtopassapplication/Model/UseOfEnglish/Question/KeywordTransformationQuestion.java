package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;


import androidx.annotation.NonNull;

public class KeywordTransformationQuestion {

    @NonNull
    private String questionTitle;

    @NonNull
    private String questionBody;

    private boolean complete;


    public KeywordTransformationQuestion(@NonNull String questionTitle,@NonNull String questionBody) {
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

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }

}

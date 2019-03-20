package com.englishtopass.englishtopassapplication.Model.Listening.Questions;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;

import androidx.annotation.NonNull;

public class BlankFillingQuestion extends ModelUoeParent {


    @NonNull
    private String questionBody;

    @NonNull
    private boolean complete;

    public BlankFillingQuestion(@NonNull String title, @NonNull String questionBody) {
        super(title);
        this.questionBody = questionBody;
        this.complete = false;
    }

    @NonNull
    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(@NonNull String questionBody) {
        this.questionBody = questionBody;
    }

    @NonNull
    public boolean isComplete() {
        return complete;
    }

    public void setComplete(@NonNull boolean complete) {
        this.complete = complete;
    }
}


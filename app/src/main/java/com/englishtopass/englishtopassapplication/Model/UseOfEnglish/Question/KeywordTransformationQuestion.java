package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;


import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;

import androidx.annotation.NonNull;

public class KeywordTransformationQuestion extends ModelUoeParent {

    @NonNull
    private String questionBody;

    private boolean complete;


    public KeywordTransformationQuestion(@NonNull String title,@NonNull String questionBody) {
        super(title);
        this.questionBody = questionBody;
        this.timeElapsed = 0;

    }

    @NonNull
    public String getQuestionTitle() {
        return title;
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

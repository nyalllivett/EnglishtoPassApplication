package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;

import androidx.annotation.NonNull;

public class WordFormationQuestion extends ModelUoeParent {

    @NonNull
    private String questionBody;

    private boolean complete;

    public WordFormationQuestion(@NonNull String title, @NonNull String questionBody) {
        super(title);
        this.questionBody = questionBody;
        this.complete = false;
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

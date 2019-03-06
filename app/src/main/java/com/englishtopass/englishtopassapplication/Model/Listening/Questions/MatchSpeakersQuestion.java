package com.englishtopass.englishtopassapplication.Model.Listening.Questions;

import androidx.annotation.NonNull;

public class MatchSpeakersQuestion {
    @NonNull
    private String questionTitle;

    @NonNull
    private String questionBody;

    @NonNull
    private boolean complete;

    public MatchSpeakersQuestion(@NonNull String questionTitle, @NonNull String questionBody) {
        this.questionTitle = questionTitle;
        this.questionBody = questionBody;
        this.complete = false;
    }

    @NonNull
    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(@NonNull String questionTitle) {
        this.questionTitle = questionTitle;
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

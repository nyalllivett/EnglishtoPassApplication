package com.englishtopass.englishtopassapplication.Model.Listening.Questions.Parent;

import com.englishtopass.englishtopassapplication.Model.ModelParent;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ListeningParent extends ModelParent {

    private boolean[] answersAreCorrect;

    public ListeningParent(String title, String instructions, String type, boolean[] answersAreCorrect) {
        super(title, type, instructions);
        this.answersAreCorrect = answersAreCorrect;
    }

    public boolean[] getAnswersAreCorrect() {
        return answersAreCorrect;
    }

    public void setAnswersAreCorrect(boolean[] answersAreCorrect) {
        this.answersAreCorrect = answersAreCorrect;
    }

}

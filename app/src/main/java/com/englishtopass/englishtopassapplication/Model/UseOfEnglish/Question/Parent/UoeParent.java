package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent;

import com.englishtopass.englishtopassapplication.Model.ModelParent;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class UoeParent extends ModelParent {

    private boolean[] answersAreCorrect;

    public UoeParent(String title, String instructions, String type, boolean[] answers) {
        super(title, type, instructions);
        this.answersAreCorrect = answers;
    }

    public boolean[] getAnswersAreCorrect() {
        return answersAreCorrect;
    }

    public void setAnswersAreCorrect(boolean[] answersAreCorrect) {
        this.answersAreCorrect = answersAreCorrect;
    }

}

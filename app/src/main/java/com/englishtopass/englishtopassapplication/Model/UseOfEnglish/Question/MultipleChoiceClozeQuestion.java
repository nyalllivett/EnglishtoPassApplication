package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class MultipleChoiceClozeQuestion {

    private String title;
    private String body;
    private long timeElapsed;
    private ArrayList<String> answerGroupOne;
    private ArrayList<String> answerGroupTwo;
    private ArrayList<String> answerGroupThree;
    private ArrayList<String> answerGroupFour;
    private ArrayList<String> answerGroupFive;
    private ArrayList<String> answerGroupSix;
    private ArrayList<String> answerGroupSeven;
    private ArrayList<String> answerGroupEight;
    private ArrayList<String> correctAnswerGroup;
    private String exampleAnswer;
    private boolean complete;


    public MultipleChoiceClozeQuestion(@NonNull String title,@NonNull String body,@NonNull ArrayList<String> answerGroupOne,
                                       @NonNull ArrayList<String> answerGroupTwo,@NonNull ArrayList<String> answerGroupThree,
                                       @NonNull ArrayList<String> answerGroupFour,@NonNull ArrayList<String> answerGroupFive,
                                       @NonNull ArrayList<String> answerGroupSix,@NonNull ArrayList<String> answerGroupSeven,
                                       @NonNull ArrayList<String> answerGroupEight,@NonNull ArrayList<String> correctAnswerGroup
                                       ) {
        this.title = title;
        this.body = body;
        this.timeElapsed = 0L;
        this.answerGroupOne = answerGroupOne;
        this.answerGroupTwo = answerGroupTwo;
        this.answerGroupThree = answerGroupThree;
        this.answerGroupFour = answerGroupFour;
        this.answerGroupFive = answerGroupFive;
        this.answerGroupSix = answerGroupSix;
        this.answerGroupSeven = answerGroupSeven;
        this.answerGroupEight = answerGroupEight;
        this.correctAnswerGroup = correctAnswerGroup;
        this.complete = false;

    }



    public MultipleChoiceClozeQuestion(@NonNull String title, @NonNull String body,
                                       @NonNull ArrayList<String> answerGroupOne,
                                       @NonNull String exampleAnswer) {
        this.title = title;
        this.body = body;
        this.answerGroupOne = answerGroupOne;
        this.exampleAnswer = exampleAnswer;

    }

    public MultipleChoiceClozeQuestion(@NonNull String title, @NonNull String body) {
        this.title = title;
        this.body = body;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public ArrayList<String> getAnswerGroupOne() {
        return answerGroupOne;
    }

    public void setAnswerGroupOne(ArrayList<String> answerGroupOne) {
        this.answerGroupOne = answerGroupOne;
    }

    public ArrayList<String> getAnswerGroupTwo() {
        return answerGroupTwo;
    }

    public void setAnswerGroupTwo(ArrayList<String> answerGroupTwo) {
        this.answerGroupTwo = answerGroupTwo;
    }

    public ArrayList<String> getAnswerGroupThree() {
        return answerGroupThree;
    }

    public void setAnswerGroupThree(ArrayList<String> answerGroupThree) {
        this.answerGroupThree = answerGroupThree;
    }

    public ArrayList<String> getAnswerGroupFour() {
        return answerGroupFour;
    }

    public void setAnswerGroupFour(ArrayList<String> answerGroupFour) {
        this.answerGroupFour = answerGroupFour;
    }

    public ArrayList<String> getAnswerGroupFive() {
        return answerGroupFive;
    }

    public void setAnswerGroupFive(ArrayList<String> answerGroupFive) {
        this.answerGroupFive = answerGroupFive;
    }

    public ArrayList<String> getAnswerGroupSix() {
        return answerGroupSix;
    }

    public void setAnswerGroupSix(ArrayList<String> answerGroupSix) {
        this.answerGroupSix = answerGroupSix;
    }

    public ArrayList<String> getAnswerGroupSeven() {
        return answerGroupSeven;
    }

    public void setAnswerGroupSeven(ArrayList<String> answerGroupSeven) {
        this.answerGroupSeven = answerGroupSeven;
    }

    public ArrayList<String> getAnswerGroupEight() {
        return answerGroupEight;
    }

    public void setAnswerGroupEight(ArrayList<String> answerGroupEight) {
        this.answerGroupEight = answerGroupEight;
    }

    public ArrayList<String> getCorrectAnswerGroup() {
        return correctAnswerGroup;
    }

    public void setCorrectAnswerGroup(ArrayList<String> correctAnswerGroup) {
        this.correctAnswerGroup = correctAnswerGroup;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getExampleAnswer() {
        return exampleAnswer;
    }

    public void setExampleAnswer(String exampleAnswer) {
        this.exampleAnswer = exampleAnswer;
    }
}

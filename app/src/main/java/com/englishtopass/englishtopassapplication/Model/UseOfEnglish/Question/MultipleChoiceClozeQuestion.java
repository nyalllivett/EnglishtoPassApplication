package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "multiple_choice_cloze_table", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = UseOfEnglishPackage.class, parentColumns = "id", childColumns = "uoe_id"))
public class MultipleChoiceClozeQuestion extends UoeParent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "uoe_id", index = true)
    private int uoeId;

    private String body;

//    private ArrayList<String> answerGroupOne;
//    private ArrayList<String> answerGroupTwo;
//    private ArrayList<String> answerGroupThree;
//    private ArrayList<String> answerGroupFour;
//    private ArrayList<String> answerGroupFive;
//    private ArrayList<String> answerGroupSix;
//    private ArrayList<String> answerGroupSeven;
//    private ArrayList<String> answerGroupEight;
//    private ArrayList<String> correctAnswerGroup;
    private String exampleAnswer;
    private boolean complete;


//    @Ignore
//    public MultipleChoiceClozeQuestion(@NonNull String title, int uoeId, @NonNull String body, @NonNull ArrayList<String> answerGroupOne,
//                                       @NonNull ArrayList<String> answerGroupTwo, @NonNull ArrayList<String> answerGroupThree,
//                                       @NonNull ArrayList<String> answerGroupFour, @NonNull ArrayList<String> answerGroupFive,
//                                       @NonNull ArrayList<String> answerGroupSix, @NonNull ArrayList<String> answerGroupSeven,
//                                       @NonNull ArrayList<String> answerGroupEight, @NonNull ArrayList<String> correctAnswerGroup
//    ) {
//
//
//        super(title);
//        this.uoeId = uoeId;
//        this.body = body;
////        this.answerGroupOne = answerGroupOne;
////        this.answerGroupTwo = answerGroupTwo;
////        this.answerGroupThree = answerGroupThree;
////        this.answerGroupFour = answerGroupFour;
////        this.answerGroupFive = answerGroupFive;
////        this.answerGroupSix = answerGroupSix;
////        this.answerGroupSeven = answerGroupSeven;
////        this.answerGroupEight = answerGroupEight;
////        this.correctAnswerGroup = correctAnswerGroup;
//        this.complete = false;
//
//    }


    @Ignore
    public MultipleChoiceClozeQuestion(@NonNull String title, @NonNull String body,
                                       @NonNull ArrayList<String> answerGroupOne,
                                       @NonNull String exampleAnswer) {
        super(title);
        this.body = body;
//        this.answerGroupOne = answerGroupOne;
        this.exampleAnswer = exampleAnswer;

    }



    public MultipleChoiceClozeQuestion(@NonNull String title, int uoeId, @NonNull String body) {
        super(title);
        this.uoeId = uoeId;
        this.body = body;
        this.testTimeElapsed = 0L;
        this.complete = false;

    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

//    public ArrayList<String> getAnswerGroupOne() {
//        return answerGroupOne;
//    }
//
//    public void setAnswerGroupOne(ArrayList<String> answerGroupOne) {
//        this.answerGroupOne = answerGroupOne;
//    }
//
//    public ArrayList<String> getAnswerGroupTwo() {
//        return answerGroupTwo;
//    }
//
//    public void setAnswerGroupTwo(ArrayList<String> answerGroupTwo) {
//        this.answerGroupTwo = answerGroupTwo;
//    }
//
//    public ArrayList<String> getAnswerGroupThree() {
//        return answerGroupThree;
//    }
//
//    public void setAnswerGroupThree(ArrayList<String> answerGroupThree) {
//        this.answerGroupThree = answerGroupThree;
//    }
//
//    public ArrayList<String> getAnswerGroupFour() {
//        return answerGroupFour;
//    }
//
//    public void setAnswerGroupFour(ArrayList<String> answerGroupFour) {
//        this.answerGroupFour = answerGroupFour;
//    }
//
//    public ArrayList<String> getAnswerGroupFive() {
//        return answerGroupFive;
//    }
//
//    public void setAnswerGroupFive(ArrayList<String> answerGroupFive) {
//        this.answerGroupFive = answerGroupFive;
//    }
//
//    public ArrayList<String> getAnswerGroupSix() {
//        return answerGroupSix;
//    }
//
//    public void setAnswerGroupSix(ArrayList<String> answerGroupSix) {
//        this.answerGroupSix = answerGroupSix;
//    }
//
//    public ArrayList<String> getAnswerGroupSeven() {
//        return answerGroupSeven;
//    }
//
//    public void setAnswerGroupSeven(ArrayList<String> answerGroupSeven) {
//        this.answerGroupSeven = answerGroupSeven;
//    }
//
//    public ArrayList<String> getAnswerGroupEight() {
//        return answerGroupEight;
//    }
//
//    public void setAnswerGroupEight(ArrayList<String> answerGroupEight) {
//        this.answerGroupEight = answerGroupEight;
//    }
//
//    public ArrayList<String> getCorrectAnswerGroup() {
//        return correctAnswerGroup;
//    }
//
//    public void setCorrectAnswerGroup(ArrayList<String> correctAnswerGroup) {
//        this.correctAnswerGroup = correctAnswerGroup;
//    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUoeId() {
        return uoeId;
    }


}

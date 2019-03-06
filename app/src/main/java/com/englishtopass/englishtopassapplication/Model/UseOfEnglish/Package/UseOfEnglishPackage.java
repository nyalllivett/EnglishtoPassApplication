package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "use_of_english_questions_table")
public class UseOfEnglishPackage {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private MultipleChoiceClozeQuestion multipleChoiceClozeQuestion;

    @NonNull
    private OpenClozeQuestion openClozeQuestion;

    @NonNull
    private KeywordTransformationQuestion keywordTransformationQuestion;

    @NonNull
    private WordFormationQuestion wordFormationQuestion;

    public UseOfEnglishPackage(@NonNull MultipleChoiceClozeQuestion multipleChoiceClozeQuestion,
                               @NonNull OpenClozeQuestion openClozeQuestion,
                               @NonNull KeywordTransformationQuestion keywordTransformationQuestion,
                               @NonNull WordFormationQuestion wordFormationQuestion) {

        this.multipleChoiceClozeQuestion = multipleChoiceClozeQuestion;
        this.openClozeQuestion = openClozeQuestion;
        this.keywordTransformationQuestion = keywordTransformationQuestion;
        this.wordFormationQuestion = wordFormationQuestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public MultipleChoiceClozeQuestion getMultipleChoiceClozeQuestion() {
        return multipleChoiceClozeQuestion;
    }

    public void setMultipleChoiceClozeQuestion(@NonNull MultipleChoiceClozeQuestion multipleChoiceClozeQuestion) {
        this.multipleChoiceClozeQuestion = multipleChoiceClozeQuestion;
    }

    @NonNull
    public OpenClozeQuestion getOpenClozeQuestion() {
        return openClozeQuestion;
    }

    public void setOpenClozeQuestion(@NonNull OpenClozeQuestion openClozeQuestion) {
        this.openClozeQuestion = openClozeQuestion;
    }

    @NonNull
    public KeywordTransformationQuestion getKeywordTransformationQuestion() {
        return keywordTransformationQuestion;
    }

    public void setKeywordTransformationQuestion(@NonNull KeywordTransformationQuestion keywordTransformationQuestion) {
        this.keywordTransformationQuestion = keywordTransformationQuestion;
    }

    @NonNull
    public WordFormationQuestion getWordFormationQuestion() {
        return wordFormationQuestion;
    }

    public void setWordFormationQuestion(@NonNull WordFormationQuestion wordFormationQuestion) {
        this.wordFormationQuestion = wordFormationQuestion;
    }
}

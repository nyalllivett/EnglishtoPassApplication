package com.englishtopass.englishtopassapplication.Model.Listening.Package;

import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningMultipleSituationsQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "listening_questions_table")
public class ListeningPackage {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private ListeningMultipleSituationsQuestion listeningMultipleSituationsQuestion;

    @NonNull
    private BlankFillingQuestion blankFillingQuestion;

    @NonNull
    private MatchSpeakersQuestion matchSpeakersQuestion;

    @NonNull
    private ListeningOneSituationQuestion listeningOneSituationQuestion;

    private boolean complete;

    public ListeningPackage(@NonNull ListeningMultipleSituationsQuestion listeningMultipleSituationsQuestion,
                            @NonNull BlankFillingQuestion blankFillingQuestion,
                            @NonNull MatchSpeakersQuestion matchSpeakersQuestion,
                            @NonNull ListeningOneSituationQuestion listeningOneSituationQuestion) {

        this.id = id;
        this.listeningMultipleSituationsQuestion = listeningMultipleSituationsQuestion;
        this.blankFillingQuestion = blankFillingQuestion;
        this.matchSpeakersQuestion = matchSpeakersQuestion;
        this.listeningOneSituationQuestion = listeningOneSituationQuestion;
        this.complete = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public ListeningMultipleSituationsQuestion getListeningMultipleSituationsQuestion() {
        return listeningMultipleSituationsQuestion;
    }

    public void setListeningMultipleSituationsQuestion(@NonNull ListeningMultipleSituationsQuestion listeningMultipleSituationsQuestion) {
        this.listeningMultipleSituationsQuestion = listeningMultipleSituationsQuestion;
    }

    @NonNull
    public BlankFillingQuestion getBlankFillingQuestion() {
        return blankFillingQuestion;
    }

    public void setBlankFillingQuestion(@NonNull BlankFillingQuestion blankFillingQuestion) {
        this.blankFillingQuestion = blankFillingQuestion;
    }

    @NonNull
    public MatchSpeakersQuestion getMatchSpeakersQuestion() {
        return matchSpeakersQuestion;
    }

    public void setMatchSpeakersQuestion(@NonNull MatchSpeakersQuestion matchSpeakersQuestion) {
        this.matchSpeakersQuestion = matchSpeakersQuestion;
    }

    @NonNull
    public ListeningOneSituationQuestion getListeningOneSituationQuestion() {
        return listeningOneSituationQuestion;
    }

    public void setListeningOneSituationQuestion(@NonNull ListeningOneSituationQuestion listeningOneSituationQuestion) {
        this.listeningOneSituationQuestion = listeningOneSituationQuestion;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

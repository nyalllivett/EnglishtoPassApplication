package com.englishtopass.englishtopassapplication.Model.Listening.Package;

import com.englishtopass.englishtopassapplication.Enums.TestCompletion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningMultipleSituationsQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "listening_package_table")
public class ListeningPackage {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String listeningMultipleSituationsTitle;

    private String blankFillingTitle;

    private String matchSpeakersTitle;

    private String listeningOneSituationTitle;

    private long testTimeElapsed;

    private TestCompletion testCompletion;

    public ListeningPackage(String listeningMultipleSituationsTitle,
                            String blankFillingTitle,
                            String matchSpeakersTitle,
                            String listeningOneSituationTitle) {

        this.listeningMultipleSituationsTitle = listeningMultipleSituationsTitle;
        this.blankFillingTitle = blankFillingTitle;
        this.matchSpeakersTitle = matchSpeakersTitle;
        this.listeningOneSituationTitle = listeningOneSituationTitle;
        this.testTimeElapsed = 0L;
        this.testCompletion = TestCompletion.NOT_STARTED;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListeningMultipleSituationsTitle() {
        return listeningMultipleSituationsTitle;
    }

    public void setListeningMultipleSituationsTitle(String listeningMultipleSituationsTitle) {
        this.listeningMultipleSituationsTitle = listeningMultipleSituationsTitle;
    }

    public String getBlankFillingTitle() {
        return blankFillingTitle;
    }

    public void setBlankFillingTitle(String blankFillingTitle) {
        this.blankFillingTitle = blankFillingTitle;
    }

    public String getMatchSpeakersTitle() {
        return matchSpeakersTitle;
    }

    public void setMatchSpeakersTitle(String matchSpeakersTitle) {
        this.matchSpeakersTitle = matchSpeakersTitle;
    }

    public String getListeningOneSituationTitle() {
        return listeningOneSituationTitle;
    }

    public void setListeningOneSituationTitle(String listeningOneSituationTitle) {
        this.listeningOneSituationTitle = listeningOneSituationTitle;
    }

    public long getTestTimeElapsed() {
        return testTimeElapsed;
    }

    public void setTestTimeElapsed(long testTimeElapsed) {
        this.testTimeElapsed = testTimeElapsed;
    }

    public TestCompletion getTestCompletion() {
        return testCompletion;
    }

    public void setTestCompletion(TestCompletion testCompletion) {
        this.testCompletion = testCompletion;
    }
}

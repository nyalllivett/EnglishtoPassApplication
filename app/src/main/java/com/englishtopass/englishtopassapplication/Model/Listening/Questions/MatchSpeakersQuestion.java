package com.englishtopass.englishtopassapplication.Model.Listening.Questions;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.Parent.ListeningParent;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "match_speakers_table", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = ListeningPackage.class, parentColumns = "id", childColumns = "listening_id"))
public class MatchSpeakersQuestion extends ListeningParent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "listening_id", index = true)
    private int listeningId;

    @NonNull
    private String questionBody;

    public MatchSpeakersQuestion(String title, String instructions, int listeningId, @NonNull String questionBody) {
        super(title, instructions, "Match Speakers", new boolean[]{ false, false, false, false, false, false, false});
        this.listeningId = listeningId;
        this.questionBody = questionBody;
        this.complete = false;
    }

    @NonNull
    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(@NonNull String questionBody) {
        this.questionBody = questionBody;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListeningId() {
        return listeningId;
    }

    public void setListeningId(int listeningId) {
        this.listeningId = listeningId;
    }
}

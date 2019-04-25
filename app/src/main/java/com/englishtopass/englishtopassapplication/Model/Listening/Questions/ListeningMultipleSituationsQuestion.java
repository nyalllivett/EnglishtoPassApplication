package com.englishtopass.englishtopassapplication.Model.Listening.Questions;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.Parent.ListeningParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "listening_multiple_situations_table", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = ListeningPackage.class, parentColumns = "id", childColumns = "listening_id"))
public class ListeningMultipleSituationsQuestion extends ListeningParent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "listening_id", index = true)
    private int listeningId;

    @NonNull
    private String questionBody;

    private long partTimeElapsed;

    @NonNull
    private boolean complete;

    public ListeningMultipleSituationsQuestion(@NonNull String title, int listeningId, @NonNull String questionBody) {
        super(title);
        this.questionBody = questionBody;
        this.listeningId = listeningId;
        this.partTimeElapsed = 0L;
        this.complete = false;
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

    public int getListeningId() {
        return listeningId;
    }

    public void setListeningId(int listeningId) {
        this.listeningId = listeningId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPartTimeElapsed() {
        return partTimeElapsed;
    }

    public void setPartTimeElapsed(long partTimeElapsed) {
        this.partTimeElapsed = partTimeElapsed;
    }
}

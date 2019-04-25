package com.englishtopass.englishtopassapplication.Model.Reading.Questions;

import com.englishtopass.englishtopassapplication.Model.Reading.Package.ReadingPackage;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.Parent.ReadingParent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "multiple_choice_table", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = ReadingPackage.class, parentColumns = "id", childColumns = "reading_id"))
public class MultipleChoiceQuestion extends ReadingParent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(index = true)
    private int reading_id;

    private String body;

    private boolean complete;

    private long timeElapsed;

    public MultipleChoiceQuestion(String title, int reading_id, String body) {
        super(title);
        this.reading_id = reading_id;
        this.body = body;
        this.complete = false;
        this.timeElapsed = 0L;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReading_id() {
        return reading_id;
    }

    public void setReading_id(int reading_id) {
        this.reading_id = reading_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}


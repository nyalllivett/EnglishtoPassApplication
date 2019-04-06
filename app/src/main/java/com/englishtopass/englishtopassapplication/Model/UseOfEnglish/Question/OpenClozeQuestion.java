package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "open_cloze_table", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = UseOfEnglishPackage.class, parentColumns = "id", childColumns = "uoe_id"))
public class OpenClozeQuestion extends UoeParent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "uoe_id", index = true)
    private int uoeId;

    @NonNull
    private String body;

    private boolean complete;

    public OpenClozeQuestion(@NonNull String title, int uoeId, @NonNull String body) {
        super(title);
        this.uoeId = uoeId;
        this.body = body;
        this.complete = false;
        this.testTimeElapsed = 0L;

    }

    @NonNull
    public String getBody() {
        return body;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
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

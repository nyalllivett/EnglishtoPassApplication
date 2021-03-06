package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question;

import com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe.WORD_FORMATION;

@Entity(tableName = "word_formation_table", foreignKeys =
@ForeignKey(onDelete = ForeignKey.CASCADE, entity = UseOfEnglishPackage.class, parentColumns = "id", childColumns = "uoe_id"))
public class WordFormationQuestion extends UoeParent {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "uoe_id", index = true)
    private final int uoeId;

    @NonNull
    private String body;


    public WordFormationQuestion(@NonNull String title, String instructions, int uoeId, @NonNull String body) {
        super(title, instructions, "Word Formation", new boolean[]{false});
        this.uoeId = uoeId;
        this.body = body;
        this.testTimeElapsed = 0L;
    }

    @NonNull
    public String getBody() {
        return body;
    }

    public int getUoeId() {
        return uoeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

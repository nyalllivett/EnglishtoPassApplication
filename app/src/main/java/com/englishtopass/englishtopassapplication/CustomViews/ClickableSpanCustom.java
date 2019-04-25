package com.englishtopass.englishtopassapplication.CustomViews;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

import androidx.annotation.NonNull;

public abstract class ClickableSpanCustom extends ClickableSpan {

    private int tag;
    private String answerTag;
    private float rawXPosition;

    protected ClickableSpanCustom(int tag) {
        this.tag = tag;
        this.answerTag = "";
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getAnswerTag() {
        return answerTag;
    }

    public void setAnswerTag(String answerTag) {
        this.answerTag = answerTag;
    }

    public float getRawXPosition() {
        return rawXPosition;
    }

    public void setRawXPosition(float rawXPosition) {
        this.rawXPosition = rawXPosition;
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }


}

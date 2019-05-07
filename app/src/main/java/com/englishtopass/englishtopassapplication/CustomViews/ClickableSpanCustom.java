package com.englishtopass.englishtopassapplication.CustomViews;

import android.text.style.ClickableSpan;

public abstract class ClickableSpanCustom extends ClickableSpan {

    private int tag;
    private String answerTag;
//    private float rawXPosition;

    protected ClickableSpanCustom(int tag) {
        this.tag = tag;
        this.answerTag = "########";
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

}

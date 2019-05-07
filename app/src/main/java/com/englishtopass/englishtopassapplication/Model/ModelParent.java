package com.englishtopass.englishtopassapplication.Model;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ModelParent {

    protected String title;
    protected long testTimeElapsed;
    protected String type;
    protected boolean complete;
    protected String instructions;



    public ModelParent(String title, String type, String instructions) {
        this.title = title;
        this.testTimeElapsed = 0L;
        this.type = type;
        this.complete = false;
        this.instructions = instructions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTestTimeElapsed() {
        return testTimeElapsed;
    }

    public void setTestTimeElapsed(long testTimeElapsed) {
        this.testTimeElapsed = testTimeElapsed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String timeElapsedToString() {

        long currentMillis = getTestTimeElapsed();

        long hours = TimeUnit.MILLISECONDS.toHours(currentMillis);
        long minute = TimeUnit.MILLISECONDS.toMinutes(currentMillis) - (TimeUnit.MILLISECONDS.toHours(currentMillis)* 60);
        long second = TimeUnit.MILLISECONDS.toSeconds(currentMillis) - (TimeUnit.MILLISECONDS.toMinutes(currentMillis) * 60);

        return String.format(Locale.getDefault(), "%01d:%02d:%02d",hours, minute,second);

    }



}

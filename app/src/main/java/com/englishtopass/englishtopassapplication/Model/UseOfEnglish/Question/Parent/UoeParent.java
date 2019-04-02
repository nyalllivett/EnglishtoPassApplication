package com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class UoeParent {

    protected String title;
    protected long testTimeElapsed;

    public UoeParent(String title) {
        this.title = title;
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

    public String timeElapsedToString() {

        long currentMillis = getTestTimeElapsed();

        long hours = TimeUnit.MILLISECONDS.toHours(currentMillis);
        long minute = TimeUnit.MILLISECONDS.toMinutes(currentMillis) - (TimeUnit.MILLISECONDS.toHours(currentMillis)* 60);
        long second = TimeUnit.MILLISECONDS.toSeconds(currentMillis) - (TimeUnit.MILLISECONDS.toMinutes(currentMillis) * 60);

        return String.format(Locale.getDefault(), "%01d:%02d:%02d",hours, minute,second);

    }
}

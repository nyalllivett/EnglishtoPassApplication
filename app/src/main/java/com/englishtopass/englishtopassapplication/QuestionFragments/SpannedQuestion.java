package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.CustomViews.ClickableSpanCustom;
import com.englishtopass.englishtopassapplication.SpanByTagComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class SpannedQuestion extends Question {

    protected TextView questionBody;

    protected SpannableStringBuilder spannableStringBuilder;

    protected Pattern pattern;

    int spanSelected;

    protected int[] spanIndices = new int[2];

    protected void sqCompileRegexPattern(String string) {

        pattern = Pattern.compile(string);

    }

    protected void sqReplaceAndSetBuilderText(String string) {
        sqSetClickableText(spannableStringBuilder.replace(sqGetCurrentSpanIndices()[0], sqGetCurrentSpanIndices()[1], string));
    }

    protected void sqSetClickableText(SpannableStringBuilder stringBuilder){

        questionBody.setText(stringBuilder);

//        hideActionFrame();

    }

    /**
     * retrieves all spans from the spannable string builder and creates a List
     * with them. Then they are sorted ASC from the tag int
     * @return
     * list of spans from the spannable string builder
     */
    protected List<ClickableSpanCustom> sqReturnOrderedSpanList() {

        List<ClickableSpanCustom> clickableSpans = Arrays.asList(spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), ClickableSpanCustom.class));

        Collections.sort(clickableSpans, new SpanByTagComparator());

        return clickableSpans;

    }


    /**
     * returns the current span that is selected
     * called from answer main click to make sure the button tag is the same as span selected
     * called from open action bar to get the correct partition of answers for the span clicked
     * called from
     * @return
     */
    protected int sqGetSpanSelected() {
        return spanSelected;
    }

    // Called from onCreate & span onClick() & main onClick() & hideActionFrame()
    protected void sqSetSpanSelected(int spanSelected) {
        this.spanSelected = spanSelected;
    }


    /**
     * returns the current start index and end index for the span
     * that is selected currently. this is called when one of the multiple choice
     * answer buttons is clicked so we can know where to insert to answer
     * called from sqSetClickableText in this class
     * @return
     * 2 ints
     */
    protected int[] sqGetCurrentSpanIndices() {
        return spanIndices;
    }

    /**
     * set the the two int values of the array to the current span index start
     * and index end.
     * called from onclick of the ClickableSpanCustom span.
     * called from main onclick to de-select a span when we click the question body
     * called from hide action frame
     * @param start
     * index start
     * @param end
     * index end
     */
    protected void sqSetCurrentSpanIndices(int start, int end) {
        this.spanIndices[0] = start;
        this.spanIndices[1] = end;
    }



}

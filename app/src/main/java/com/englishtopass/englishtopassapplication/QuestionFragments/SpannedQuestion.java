package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.CustomViews.ClickableSpanCustom;
import com.englishtopass.englishtopassapplication.Interfaces.SetScrollYListener;
import com.englishtopass.englishtopassapplication.SpanByTagComparator;
import com.englishtopass.englishtopassapplication.UtilClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;

public class SpannedQuestion extends Question implements SetScrollYListener {
    private static final String TAG = "SpannedQuestion";

    protected TextView questionBody;
    /**
     * frame layout which houses the multiple choice questions.
     * initialised in the OnCreateView at high Level.
     */
    protected FrameLayout actionFrameLayout;

    /**
     * time buffer to stop accidental double click when we select a span
     */
    protected boolean bufferTimeElapsed;

    /**
     * set to true once actionFrame is open, checked in the main onClickMethod and if
     * true will close the action frame and return before any action can be taken.
     */
    private boolean actionFrameOpen;

    /**
     * this is the position of the span that is clicked to then answer. it is set
     * from the CustomLinkMovementMethod calling the interface method setSpanYPosition()
     * implemented here. its used in conjunction with actionFrameY to work out whether
     * the span will be behind the action frame once its opened.
     */
    private int spanYPosition;

    /**
     * the scroll view that holds the question body.
     */
    protected ScrollView scrollView;

    /**
     * the string builder that creates the clickable spans
     */
    protected SpannableStringBuilder spannableStringBuilder;

    /**
     * the pattern used for the regex, set in the onCreate method at high level to what
     * pattern is needed for the question
     */
    protected Pattern pattern;

    /**
     * is which span has been clicked, set by the clickable span tag, with the onClick
     * method. gets reset in the method hide action frame as focus has been shifted
     */
    protected int spanSelected;

    /**
     * the two values here are the clicked spans index start and index end. used for
     * inserting the answer into the correct position, set in the on click method
     * of clickable span. also reset in the hide action frame method
     */
    protected int[] spanIndices = new int[2];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * compiles the pattern for the matcher to find in the searchable string
     * @param string
     * regex pattern
     */
    protected void sqCompileRegexPattern(String string) {
        pattern = Pattern.compile(string);
    }

    /**
     * called when and answer has been entered or selected to replace the span
     * text with answer.
     * checks whether its an empty string first.
     * then call the setClickableText method to refresh the textView
     * @param string
     * the answers that the user has clickec
     */
    protected void sqReplaceAndSetBuilderText(String string) {
        if (UtilClass.isStringWhiteSpace(string))
            sqSetClickableText(spannableStringBuilder.replace(sqGetCurrentSpanIndices()[0], sqGetCurrentSpanIndices()[1], string));
    }

    /**
     * take the new/altered spannableStringBuilder and sets it back to textView
     * @param stringBuilder
     * SpannableStringBuilder
     */
    protected void sqSetClickableText(SpannableStringBuilder stringBuilder){
        questionBody.setText(stringBuilder);
    }

    /**
     * retrieves all spans from the spannable string builder and creates a List
     * with them. Then they are sorted ASC from the tag int
     * called from answer on click to return list to iterate through to set answer tag on the tag
     * called from getCheckedAnswers() so be able to access booelan array of answers
     * @return
     * list of spans from the spannable string builder
     */
    protected List<ClickableSpanCustom> sqReturnOrderedSpanList() {

        List<ClickableSpanCustom> clickableSpans = Arrays.asList(spannableStringBuilder.getSpans(
                0, spannableStringBuilder.length(), ClickableSpanCustom.class));
        Collections.sort(clickableSpans, new SpanByTagComparator());
        return clickableSpans;
    }

    /**
     * iterates through ASC ordered list of clickable spans and also qGetCorrectAnswers() and compares,
     * if they match the new answers array will be updated and returned once complete.
     * called at the end at the test to set the model answersArray
     * i think possibly different for every type of question as some don't have spans
     * @return
     * array of booleans corresponding to whether that answer is correct
     */
    protected boolean[] sqGetCheckedAnswerBooleans() {
        int i = 0;
        boolean[] answers = new boolean[8];
        for (ClickableSpanCustom span : sqReturnOrderedSpanList()) {
            answers[i] = span.getAnswerTag().equals(qGetCorrectAnswers()[i]);
            i++;
        }
        return answers;
    }

    /**
     * returns the current span that is selected
     * called from answer main click to make sure the button tag is the same as span selected
     * called from open action bar to get the correct partition of answers for the span clicked
     * called from onCreate to initialize the field
     * @return
     */
    protected int sqGetSpanSelected() {
        return spanSelected;
    }

    /**
     * sets the currently selected span ready to answer question.
     * called from span onClick set by getting span tag
     * called from sqHideActionFrame to reset span selected
     * called from sqHideActionFrame to reset span selected
     * @param spanSelected
     */
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


    /**
     * set whether frame is open of not
     * called in sqOpenActionFrame() and close sqHideActionFrame()
     * @param actionFrameOpen
     */
    protected void sqSetActionFrameOpen(boolean actionFrameOpen) {
        this.actionFrameOpen = actionFrameOpen;
    }

    /**
     * return whether action frame is open, used to see whether a scroll of the question body is
     * needed to keep the selected span visible.
     * also used to see whether the action bar should close if there is a click off focused item to
     * hide action frame.
     * @return
     * boolean whehter frame is open or not
     */
    protected boolean sqIsActionFrameOpen() {
        return actionFrameOpen;
    }

    /**
     * checks to see whether action bar is open, if so scrolls do past the span thats selected
     * if it needs to else if action bar is not open anymore then it will scroll back to where
     * it started.
     * if the scroll amount it 0 no action will be taken.
     */
    protected void sqScrollQuestionText(){
        int scrollAmount = (int) (sqGetSpanYPosition() - actionFrameLayout.getY());
        if (!sqIsActionFrameOpen()) {
            scrollView.scrollTo(0, -scrollAmount);
            return;
        }
        if (scrollAmount > 0) {
            scrollView.scrollTo(0, (scrollAmount) + 20);
        }
    }

    /**
     * this interface method sets the position of the current span clicked from
     * LinkMovementMethod. the coord is from an on touch listener NOT the actual
     * span itself
     * @param yCoord
     * the OnTouch Y position
     */
    @Override
    public void setSpanYPosition(int yCoord) {
        this.spanYPosition = yCoord;
    }

    /**
     * gets Y position of the span clicked
     * @return
     * y axis of span clicked
     */
    private int sqGetSpanYPosition() {
        return spanYPosition;
    }

    /**
     * find the span by tag and returns once found.
     * @param currentTag
     * the tag which we search with
     * @return
     * span with the correct tag
     */
    public ClickableSpanCustom findSpanByTag(int currentTag){

        for (ClickableSpanCustom temp : spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), ClickableSpanCustom.class)) {
            if (temp.getTag() == currentTag) return temp;
        }
        return null;
    }

    /**
     * open action frame doesnt actually open the action frame as its always there
     * just with no height, it set a listener for the change of height, once that
     * listener is triggered it means another view has become its child, therefore
     * setting actionFrame open. there is runnable in there setting a boolean after
     * 10ms to stop double clicking accidently which was happening. this is should hopefully
     * be somewhat of a universial method so how. not sure yet
     */
    protected void sqOpenActionFrame() {
        actionFrameLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        actionFrameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        sqSetActionFrameOpen(true);
                        bufferTimeElapsed = false;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                bufferTimeElapsed = true;
                            }
                        }, 100);
                        sqScrollQuestionText();
                    }
                }
        );
    }

    /**
     * removes all children from the frame layout, scrolls back to original
     * position and basically resets the span fields.
     */
    protected void sqHideActionFrame() {

        actionFrameLayout.removeAllViews();

        sqSetActionFrameOpen(false);

        sqScrollQuestionText();

        sqSetSpanSelected(-1);

        sqSetCurrentSpanIndices(-1, -1);

    }
}

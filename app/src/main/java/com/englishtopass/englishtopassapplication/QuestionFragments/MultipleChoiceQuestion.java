package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.CustomViews.ClickableSpanCustom;
import com.englishtopass.englishtopassapplication.Interfaces.SetScrollYListener;
import com.englishtopass.englishtopassapplication.R;

import java.util.Arrays;
import java.util.regex.Matcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MultipleChoiceQuestion extends SpannedQuestion implements SetScrollYListener {
    private static final String TAG = "MultipleChoiceAnswer";

    /**
     * partitioned answers is an 3D array
     * providing multiple choices of answers for each span.
     **/
    private String[][] partitionedAnswers = new String[8][4];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sqSetActionFrameOpen(false);

    }

    /**
     * this method is called in the OnSuccess of RxJava call
     * to the database retrieving the question.
     * called from onSuccess on retrieving the current question from rxjava
     * will be called from MULTIPLE CHOICE QUESTION ONLY
     * @param array
     * the 2D array of all answers. wrong and correct.
     * @param partitionSize
     * how many answers i want to display/inside the mcaPartition
     */
    protected void mcaPartition(String[] array, int partitionSize) {

        int partitions = array.length / partitionSize;

        for (int i = 0; i < partitions; i++) {

            partitionedAnswers[i] = Arrays.copyOfRange(array, i * partitionSize, i * partitionSize + partitionSize);

        }
    }

    /**
     * inflates the correct view inside the action frame, called after the action
     * frame has been opened and listener set.
     * this will be different for every type of question.
     */
    protected void mcaInflateMultipleChoiceView(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.multipleChoiceOne:
                    case R.id.multipleChoiceTwo:
                    case R.id.multipleChoiceThree:
                    case R.id.multipleChoiceFour:
                        if (sqGetCurrentSpanIndices()[0] != -1 && sqGetCurrentSpanIndices()[1] != -1) {
                            sqReplaceAndSetBuilderText(((Button) v).getText().toString());
                            findSpanByTag(sqGetSpanSelected()).setAnswerTag(((Button) v).getText().toString());
                            sqHideActionFrame();
                            return;
                        } else {
                            throw new IllegalStateException("Span indices incorrect");
                        }
                    case R.id.resetSpanText:
                        sqReplaceAndSetBuilderText("........");
                        break;
                    case R.id.hideActionFrame:
                        sqHideActionFrame();
                        break;
                }
            }
        };

        View view = LayoutInflater.from(getContext()).inflate(R.layout.action_frame_multiple_choice, actionFrameLayout, true);
        view.findViewById(R.id.hideActionFrame).setOnClickListener(onClickListener);
        view.findViewById(R.id.resetSpanText).setOnClickListener(onClickListener);
        LinearLayout linearLayout = view.findViewById(R.id.linearQuestions);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            Button button = (Button) linearLayout.getChildAt(i);
            button.setText(partitionedAnswers[sqGetSpanSelected()][i]);
            button.setOnClickListener(onClickListener);
        }


    }

    /**
     * method called by findPattern method to create the spans. also set the
     * onClick method at the same time.
     * @param startingIndex
     * @param endingIndex
     * @param tag
     */
    private void createClickableSpan(int startingIndex, int endingIndex, int tag){
        spannableStringBuilder.setSpan(new ClickableSpanCustom(tag) {
            @Override
            public void onClick(@NonNull View widget) {
                sqSetSpanSelected(this.getTag());
                TextView textView = (TextView) widget;
                Spannable spannable = (Spannable) textView.getText();
                sqSetCurrentSpanIndices(spannable.getSpanStart(this), spannable.getSpanEnd(this));

                if (!sqIsActionFrameOpen()) {
                    sqOpenActionFrame();
                    mcaInflateMultipleChoiceView();
                }
            }
        }, startingIndex, endingIndex, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
    }


    /**
     * finds the blanks in the searchable string provided and when found will
     * a clickable span in the corresponding position in the spannable
     * string builder
     * @param body
     */
    protected void findPattern(String body){
        Matcher matcher = pattern.matcher(body);
        int tagCounter = 0;
        while (matcher.find()) {
            createClickableSpan(matcher.start(), matcher.end(), tagCounter);
            tagCounter++;
        }
        questionBody.setText(spannableStringBuilder);
    }




}

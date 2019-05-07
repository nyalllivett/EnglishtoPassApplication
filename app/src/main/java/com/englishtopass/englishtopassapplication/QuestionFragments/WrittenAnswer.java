package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.englishtopass.englishtopassapplication.CustomViews.ClickableSpanCustom;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.UtilClass;

import androidx.annotation.Nullable;

public class WrittenAnswer extends SpannedQuestion {
    private static final String TAG = "WrittenAnswer";

    EditText writtenAnswerText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sqSetActionFrameOpen(false);

    }

    /**
     * inflates the correct view inside the action frame, called after the action
     * frame has been opened and listener set.
     * this will be different for every type of question.
     */
    protected void waInflateWrittenAnswerView(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.insertAnswerButton:
                        if (sqGetCurrentSpanIndices()[0] != -1 && sqGetCurrentSpanIndices()[1] != -1) {
                            sqReplaceAndSetBuilderText(writtenAnswerText.getText().toString());
                            findSpanByTag(sqGetSpanSelected()).setAnswerTag(((Button) v).getText().toString());
                            UtilClass.closeKeyboard(v);
                            sqHideActionFrame();
                            return;
                        } else {
                            throw new IllegalStateException("Span indices incorrect");
                        }
                    case R.id.resetSpanText:
                        ClickableSpanCustom clickableSpanCustom = findSpanByTag(sqGetSpanSelected());
                        sqSetCurrentSpanIndices(spannableStringBuilder.getSpanStart(clickableSpanCustom), spannableStringBuilder.getSpanEnd(clickableSpanCustom));
                        sqReplaceAndSetBuilderText("........");
                        break;
                    case R.id.hideActionFrame:
                        sqHideActionFrame();
                        break;
                }
            }
        };

        View view = LayoutInflater.from(getContext()).inflate(R.layout.action_frame_written_answer, actionFrameLayout, true);
        writtenAnswerText = view.findViewById(R.id.openClozeAnswerEditText);
        view.findViewById(R.id.insertAnswerButton).setOnClickListener(onClickListener);
        view.findViewById(R.id.hideActionFrame).setOnClickListener(onClickListener);
        view.findViewById(R.id.resetSpanText).setOnClickListener(onClickListener);



    }

}

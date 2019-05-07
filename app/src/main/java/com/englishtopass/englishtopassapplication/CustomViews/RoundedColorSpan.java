package com.englishtopass.englishtopassapplication.CustomViews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RoundedColorSpan extends DynamicDrawableSpan {
    private static final String TAG = "RoundedColorSpan";
    private static final int CORNER_RADIUS = 8;
    private static final int PADDING_X = 24;
    private static final int LINE_HEIGHT_REDUCTION = 25;

    @Override
    public Drawable getDrawable() {
        return null;
    }

    /**
     * text is the text of the text view,
     * start is the start of the span
     * end is the end of the span
     * return the size of the span in pixels i imagine
     */
    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        return (int) (PADDING_X + paint.measureText(text.subSequence(start, end).toString()) + PADDING_X);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {

        // START = CHAR NUMBER START OF SPAN
        // END = CHAR NUMBER END OF SPAN
        // X IS THE X AXIS OF SPAN

        // THIS WILL RETURN 144PX THE LENGTH OF SPAN
        float width = paint.measureText(text.subSequence(start, end).toString());
        RectF rect = new RectF(x - 1, (top + 3) - 10, x + width + 2 * PADDING_X + 1, (bottom - LINE_HEIGHT_REDUCTION) +1);
        paint.setColor(Color.parseColor("#6099FF"));
        canvas.drawRoundRect(rect, CORNER_RADIUS + 4, CORNER_RADIUS + 4, paint);
        RectF rect2 = new RectF(x +1, (top + 5) - 10 , x + width + 2 * PADDING_X - 1, (bottom - LINE_HEIGHT_REDUCTION) - 1);
        paint.setColor(Color.parseColor("#DCE9FF"));
        canvas.drawRoundRect(rect2, CORNER_RADIUS, CORNER_RADIUS, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(text, start, end, x + PADDING_X, y, paint);
    }
}

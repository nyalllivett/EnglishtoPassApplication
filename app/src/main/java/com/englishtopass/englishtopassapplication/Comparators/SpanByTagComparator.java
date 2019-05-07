package com.englishtopass.englishtopassapplication.Comparators;

import com.englishtopass.englishtopassapplication.CustomViews.ClickableSpanCustom;

import java.util.Comparator;

public class SpanByTagComparator implements Comparator<ClickableSpanCustom> {
    @Override
    public int compare(ClickableSpanCustom o1, ClickableSpanCustom o2) {
        return Integer.compare(o1.getTag(), o2.getTag());
    }
}

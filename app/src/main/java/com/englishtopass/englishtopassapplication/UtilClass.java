package com.englishtopass.englishtopassapplication;

import android.content.Context;

public class UtilClass {

    public static float pixelsToScaledDensity(Context context, float pixels){

        return pixels / context.getResources().getDisplayMetrics().scaledDensity;

    }

    public static String[] stringSplitter(String string) {

        return string.trim().split("#");

    }

}

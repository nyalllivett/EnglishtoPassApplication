package com.englishtopass.englishtopassapplication;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class UtilClass {

    public static float pixelsToScaledDensity(Context context, float pixels){

        return pixels / context.getResources().getDisplayMetrics().scaledDensity;

    }

    public static String[] stringSplitter(String string) {

        return string.trim().split("#");

    }

    public static void closeKeyboard(View view){
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static boolean isStringWhiteSpace(String string){
        return string.trim().length() > 0;
    }
}

package com.englishtopass.englishtopassapplication.Converters.ListeningConverters;


import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class BlankFillingConverter {

    @TypeConverter
    public static BlankFillingQuestion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, BlankFillingQuestion.class);

    }

    @TypeConverter
    public static String questionToString(BlankFillingQuestion keywordTransformationQuestion){

        Gson gson = new Gson();

        return (keywordTransformationQuestion == null) ? null : gson.toJson(keywordTransformationQuestion);

        }



}

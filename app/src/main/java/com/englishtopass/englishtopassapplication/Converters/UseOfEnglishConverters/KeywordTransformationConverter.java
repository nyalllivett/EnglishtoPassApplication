package com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters;


import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.KeywordTransformationQuestion;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class KeywordTransformationConverter {

    @TypeConverter
    public static KeywordTransformationQuestion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, KeywordTransformationQuestion.class);

    }

    @TypeConverter
    public static String questionToString(KeywordTransformationQuestion keywordTransformationQuestion){

        Gson gson = new Gson();

        return (keywordTransformationQuestion == null) ? null : gson.toJson(keywordTransformationQuestion);

        }



}

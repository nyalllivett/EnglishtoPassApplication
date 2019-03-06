package com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters;


import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.MultipleChoiceClozeQuestion;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class MultipleChoiceClozeConverter {

    @TypeConverter
    public static MultipleChoiceClozeQuestion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, MultipleChoiceClozeQuestion.class);

    }

    @TypeConverter
    public static String questionToString(MultipleChoiceClozeQuestion multipleChoiceClozeQuestion){

        Gson gson = new Gson();

        return (multipleChoiceClozeQuestion == null) ? null : gson.toJson(multipleChoiceClozeQuestion);

        }



}

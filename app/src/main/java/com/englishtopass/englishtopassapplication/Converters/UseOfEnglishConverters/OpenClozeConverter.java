package com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters;


import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.OpenClozeQuestion;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class OpenClozeConverter {

    @TypeConverter
    public static OpenClozeQuestion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, OpenClozeQuestion.class);

    }

    @TypeConverter
    public static String questionToString(OpenClozeQuestion openClozeQuestion){

        Gson gson = new Gson();

        return (openClozeQuestion == null) ? null : gson.toJson(openClozeQuestion);

        }



}

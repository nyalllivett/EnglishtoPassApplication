package com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters;


import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UoeCompletion;

import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class UoeEnumConverter {

    @TypeConverter
    public static UoeCompletion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, UoeCompletion.class);

    }

    @TypeConverter
    public static String questionToString(UoeCompletion uoeCompletion){

        Gson gson = new Gson();

        return (uoeCompletion == null) ? null : gson.toJson(uoeCompletion);

        }



}

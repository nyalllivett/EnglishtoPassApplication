package com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters;


import com.englishtopass.englishtopassapplication.Enums.TestCompletion;

import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class TestEnumConverter {

    @TypeConverter
    public static TestCompletion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, TestCompletion.class);

    }

    @TypeConverter
    public static String questionToString(TestCompletion testCompletion){

        Gson gson = new Gson();

        return (testCompletion == null) ? null : gson.toJson(testCompletion);

        }



}

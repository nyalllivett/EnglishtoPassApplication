package com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters;


import com.englishtopass.englishtopassapplication.Enums.QuestionPartUoe;
import com.englishtopass.englishtopassapplication.Enums.TestCompletion;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class PartEnumConverter {

    @TypeConverter
    public static QuestionPartUoe stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, QuestionPartUoe.class);

    }

    @TypeConverter
    public static String questionToString(QuestionPartUoe questionPart){

        Gson gson = new Gson();

        return (questionPart == null) ? null : gson.toJson(questionPart);

        }



}

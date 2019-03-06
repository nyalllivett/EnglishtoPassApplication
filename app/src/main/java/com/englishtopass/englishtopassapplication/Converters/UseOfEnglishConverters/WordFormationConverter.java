package com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters;


import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.WordFormationQuestion;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class WordFormationConverter {

    @TypeConverter
    public static WordFormationQuestion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, WordFormationQuestion.class);

    }

    @TypeConverter
    public static String questionToString(WordFormationQuestion wordFormationQuestion){

        Gson gson = new Gson();

        return (wordFormationQuestion == null) ? null : gson.toJson(wordFormationQuestion);

        }



}

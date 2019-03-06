package com.englishtopass.englishtopassapplication.Converters.ListeningConverters;


import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class ListeningOneSituationConverter {

    @TypeConverter
    public static ListeningOneSituationQuestion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, ListeningOneSituationQuestion.class);

    }

    @TypeConverter
    public static String questionToString(ListeningOneSituationQuestion listeningOneSituationQuestion){

        Gson gson = new Gson();

        return (listeningOneSituationQuestion == null) ? null : gson.toJson(listeningOneSituationQuestion);

        }



}

package com.englishtopass.englishtopassapplication.Converters.ListeningConverters;



import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningMultipleSituationsQuestion;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class ListeningMultipleSituationsConverter {

    @TypeConverter
    public static ListeningMultipleSituationsQuestion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, ListeningMultipleSituationsQuestion.class);

    }

    @TypeConverter
    public static String questionToString(ListeningMultipleSituationsQuestion listeningMultipleSituationsQuestion){

        Gson gson = new Gson();

        return (listeningMultipleSituationsQuestion == null) ? null : gson.toJson(listeningMultipleSituationsQuestion);

        }



}

package com.englishtopass.englishtopassapplication.Converters.ListeningConverters;


import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class MatchSpeakersConverter {

    @TypeConverter
    public static MatchSpeakersQuestion stringToQuestion(String data){

        Gson gson = new Gson();

        return (data == null) ? null : gson.fromJson(data, MatchSpeakersQuestion.class);

    }

    @TypeConverter
    public static String questionToString(MatchSpeakersQuestion matchSpeakersQuestion){

        Gson gson = new Gson();

        return (matchSpeakersQuestion == null) ? null : gson.toJson(matchSpeakersQuestion);

        }



}

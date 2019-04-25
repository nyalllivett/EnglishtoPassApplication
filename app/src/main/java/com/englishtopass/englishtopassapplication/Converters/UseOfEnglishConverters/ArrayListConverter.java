package com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters;


import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.room.TypeConverter;
public class ArrayListConverter {

    @TypeConverter
    public static ArrayList<String> stringToQuestion(String data){

        ArrayList<String> arrayList = new ArrayList<String>();
        Gson gson = new Gson();

        String[] array = data.split(",");

        for (String s : array) {
            if (!s.isEmpty()) {
                arrayList.add(s);
            }
        }

        return arrayList;


    }

    @TypeConverter
    public static String questionToString(ArrayList<String> arrayList){

        Gson gson = new Gson();

        return (arrayList == null) ? null : gson.toJson(arrayList);

        }

}

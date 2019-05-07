package com.englishtopass.englishtopassapplication.Converters.UseOfEnglishConverters;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import androidx.room.TypeConverter;

public class BooleanListConverter {
    private Gson gson = new Gson();

    @TypeConverter
    public boolean[] stringToQuestion(String data){

        ArrayList<Boolean> tempData;

        int j = data.split(",").length;

        tempData = gson.fromJson(data, new TypeToken<ArrayList<Boolean>>(){}.getType());

        boolean[] booleans = new boolean[j];

        for (int i = 0; i < tempData.size(); i++) {

            booleans[i] = tempData.get(i);

        }

        return booleans;

    }

    @TypeConverter
    public String arrayToString(boolean[] array){

        return (array == null) ? null : gson.toJson(array);

        }

}

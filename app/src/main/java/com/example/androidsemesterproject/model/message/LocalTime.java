package com.example.androidsemesterproject.model.message;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

public class LocalTime {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public org.threeten.bp.LocalTime time(String string)
    {
       return org.threeten.bp.LocalTime.parse(string);
    }

    @TypeConverter
    public String time2(org.threeten.bp.LocalTime time)
    {
       return time.toString();
    }
}

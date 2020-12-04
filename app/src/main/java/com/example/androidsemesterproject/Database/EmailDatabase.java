package com.example.androidsemesterproject.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidsemesterproject.model.email.AccountDaoInt;
import com.example.androidsemesterproject.model.email.EMail;

@Database(entities = {EMail.class},version = 1)
public abstract class EmailDatabase extends RoomDatabase {
    private static EmailDatabase instance;
    public abstract AccountDaoInt accountDAO();

    public static synchronized EmailDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                        EmailDatabase.class, "email_databse")
                        .fallbackToDestructiveMigration()
                        .build();
        }
        return instance;
    }
}

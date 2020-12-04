package com.example.androidsemesterproject.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidsemesterproject.model.message.Message;
import com.example.androidsemesterproject.model.message.MessageDAO;
import com.example.androidsemesterproject.model.message.MessageDAOInt;

@Database(entities = {Message.class},version = 1)
public abstract class MessageDatabase extends RoomDatabase {
    private static MessageDatabase instance;
    public abstract MessageDAOInt messageDAO();

    public static synchronized MessageDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MessageDatabase.class, "message_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

package com.example.androidsemesterproject.model.message;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface MessageDAOInt {
    @Query("select * from Message ")
    LiveData<List<Message>> getAllMessages();
   @Insert
    void insert(Message message);

   @Query("delete from message where id =:id")
   void deleteMessage(int id);

  // @Query("select * from Message where `from` =:from and sendTo =:sendTo and title =:title and message=:message and time=:time")
   // Message getMessage(String from, String sendTo, String title, String message, Time time);
}

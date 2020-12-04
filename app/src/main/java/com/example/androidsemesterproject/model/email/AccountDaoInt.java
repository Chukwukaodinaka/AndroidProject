package com.example.androidsemesterproject.model.email;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDaoInt {
    @Query("Select * from EMail")
    public LiveData<List<EMail>> getAllAccount();

    @Insert
    public void insert(EMail email);

    @Query("delete from EMail where domain = :domain and emailname = :emailName and extemsion = :extension")
    public void remove(String domain, String emailName, String extension);
}


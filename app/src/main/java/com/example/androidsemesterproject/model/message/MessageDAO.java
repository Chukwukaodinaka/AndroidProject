package com.example.androidsemesterproject.model.message;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.sql.Time;
import java.util.ArrayList;

public class MessageDAO {
    private MutableLiveData<ArrayList<Message>> allMessages;
    private static MessageDAO instance;

    private MessageDAO()
    {
        allMessages = new MutableLiveData<>();
        ArrayList<Message> newList = new ArrayList<>();
        allMessages.setValue(newList);
    }

    public  static MessageDAO getInstance()
    {
        if(instance == null)
        {
            instance = new MessageDAO();
        }
        return instance;
    }

    public LiveData<ArrayList<Message>> getAllMessages(){
        return allMessages;
    }

    public void insert(Message message)
    {
        ArrayList<Message> currentMessages = allMessages.getValue();
        currentMessages.add(message);
        allMessages.setValue(currentMessages);
    }



    public void deleteMessage(Message message)
    {
        ArrayList<Message> currentMessages = allMessages.getValue();
        currentMessages.remove(message);
        allMessages.setValue(currentMessages);
    }

    public Message getMessage(String from, String sendTo, String title, String message, Time time)
    {
        ArrayList<Message> currentMessages = allMessages.getValue();
        for (int i = 0; i < currentMessages.size(); i++) {
//            Message messagei =currentMessages.ge
        }
        return null ;
    }

}


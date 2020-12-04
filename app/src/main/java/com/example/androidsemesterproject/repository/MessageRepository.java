package com.example.androidsemesterproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import com.example.androidsemesterproject.Database.EmailDatabase;
import com.example.androidsemesterproject.Database.MessageDatabase;
import com.example.androidsemesterproject.model.email.AccountDaoInt;
import com.example.androidsemesterproject.model.email.EMail;
import com.example.androidsemesterproject.model.message.Message;
import com.example.androidsemesterproject.model.message.MessageDAO;
import com.example.androidsemesterproject.model.message.MessageDAOInt;

import java.util.ArrayList;
import java.util.List;

public class MessageRepository {
    private MessageDAOInt messageDAO;
    private static MessageRepository instance;
    private LiveData<List<Message>> allMessage;


    private MessageRepository(Application application){
        MessageDatabase database = MessageDatabase.getInstance(application);
        messageDAO =  database.messageDAO();
        allMessage = messageDAO.getAllMessages();
    }

    public static MessageRepository getInstance(Application app){
        if(instance==null)
        {
            instance = new MessageRepository(app);
        }
        return instance;
    }

    public LiveData<List<Message>> getAllMessages(){
        return allMessage;
    }

    public void insert(Message message)
    {
        new InsertMessageAsync(messageDAO).execute(message);
    }


    public void deleteMessage(int id)
    {
        Message message = new Message();
        message.setId(id);

        new DeleteMessageAsync(messageDAO).execute(message);
    }

    private static class InsertMessageAsync extends AsyncTask<Message,Void,Void> {
        private MessageDAOInt messageDAOInt;

        private InsertMessageAsync(MessageDAOInt messageDAOInt)
        {
            this.messageDAOInt= messageDAOInt;
        }
        @Override
        protected Void doInBackground(Message... messages) {
            messageDAOInt.insert(messages[0]);
            return null;
        }
    }

    private static class DeleteMessageAsync extends AsyncTask<Message,Void,Void> {
        private MessageDAOInt messageDAOInt;

        private DeleteMessageAsync(MessageDAOInt messageDAOInt)
        {
            this.messageDAOInt= messageDAOInt;
        }
        @Override
        protected Void doInBackground(Message... messages) {
            messageDAOInt.deleteMessage(messages[0].getId());
            return null;
        }
    }

}

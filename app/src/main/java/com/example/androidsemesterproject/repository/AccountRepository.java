package com.example.androidsemesterproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidsemesterproject.Database.EmailDatabase;
import com.example.androidsemesterproject.model.email.AccountDaoInt;
import com.example.androidsemesterproject.model.email.EMail;

import java.util.List;

public class AccountRepository {
    private AccountDaoInt accountDAO;
    private static AccountRepository instance;
    private LiveData<List<EMail>> allEmails;


    private AccountRepository(Application application){
        EmailDatabase database = EmailDatabase.getInstance(application);
        accountDAO =  database.accountDAO();
        allEmails = accountDAO.getAllAccount();
    }

    public static AccountRepository getInstance(Application app){
        if(instance==null)
        {
            instance = new AccountRepository(app);
        }
        return instance;
    }


    public LiveData<List<EMail>> getAllAccount() {
        return allEmails;
    }

    public void insert(String email,String password) {
        EMail mail = new EMail();
        mail.setPassword(password);
        mail.setFrom(email);
        new InsertAccountAsync(accountDAO).execute(mail);

    }

    public void remove(String email) {
        EMail mail = new EMail();
        mail.setFrom(email);
        new DeleteAccountAsync(accountDAO).execute(mail);
    }

    private static class InsertAccountAsync extends AsyncTask<EMail,Void,Void>{
        private AccountDaoInt accountDAO;

        private InsertAccountAsync(AccountDaoInt accountDAO)
        {
            this.accountDAO= accountDAO;
        }
        @Override
        protected Void doInBackground(EMail... eMails) {
            accountDAO.insert(eMails[0]);
            return null;
        }
    }

    private static class DeleteAccountAsync extends AsyncTask<EMail,Void,Void>{
        private AccountDaoInt accountDAO;

        private DeleteAccountAsync(AccountDaoInt accountDAO)
        {
            this.accountDAO= accountDAO;
        }
        @Override
        protected Void doInBackground(EMail... eMails) {
            accountDAO.remove(eMails[0].getDomain(),eMails[0].getEmailname(),eMails[0].getExtemsion());
            return null;
        }
    }

    public EMail get(String emailname)
    {
       EMail mail = new EMail();
        for (int i = 0; i < allEmails.getValue().size() ; i++) {
            String jh=  allEmails.getValue().get(i).fullAddress();
            if(jh.equals(emailname))
            {


                mail = allEmails.getValue().get(i);
            }
        }
        return mail;
    }

}




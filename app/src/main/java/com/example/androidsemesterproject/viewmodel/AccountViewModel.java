package com.example.androidsemesterproject.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidsemesterproject.model.email.EMail;
import com.example.androidsemesterproject.repository.AccountRepository;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {
    private AccountRepository repository;

    public AccountViewModel(Application app){
        super(app);
        repository = AccountRepository.getInstance(app);
    }

    public LiveData<List<EMail>> getAllAccount()
    {
        return repository.getAllAccount();
    }

    public void addAccount(String email,String password)
    {
        repository.insert(email,password);
    }

    public void removeAccount(String email){repository.remove(email);}

    public EMail getAccount(String emailname){
      return  repository.get(emailname);
    }



}

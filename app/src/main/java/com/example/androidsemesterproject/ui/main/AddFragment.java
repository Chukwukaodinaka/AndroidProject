package com.example.androidsemesterproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsemesterproject.R;
import com.example.androidsemesterproject.model.email.EMail;
import com.example.androidsemesterproject.viewmodel.AccountAdapter;
import com.example.androidsemesterproject.viewmodel.AccountViewModel;

import java.util.List;

public class AddFragment extends Fragment {
    TextView emailText;
    RecyclerView accountList;
    AccountAdapter accountAdapter;
    AccountViewModel accountViewModel;
    List<EMail> eMails;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_fragment, container, false);
        View view = inflater.inflate(R.layout.accountsinglerow,container,false);
//        emailText = (TextView)view.findViewById(R.id.email);
        accountViewModel = new ViewModelProvider(getActivity()).get(AccountViewModel.class);

        accountList = (RecyclerView) root.findViewById(R.id.add_rv);
        System.out.println("I came here");
        //6who4tt1
        accountList.hasFixedSize();
        accountList.setLayoutManager(new LinearLayoutManager(getContext()));
         eMails= accountViewModel.getAllAccount().getValue();
        accountAdapter = new AccountAdapter(eMails,accountViewModel);
        accountList.setAdapter(accountAdapter);

//        accountAdapter.getItemText().observe(getActivity(), new Observer<String>() {
//
//                @Override
//                public void onChanged(String s) {
//                    accountViewModel.removeAccount(s);
//                }
//
//
//
//            });

        accountViewModel.getAllAccount().observe(getActivity(), new Observer<List<EMail>>() {
            @Override
            public void onChanged(List<EMail> eMails) {
                eMails= accountViewModel.getAllAccount().getValue();
                accountAdapter = new AccountAdapter(eMails,accountViewModel);
                accountList.setAdapter(accountAdapter) ;           }
        });




        return root;
    }

    public RecyclerView getAccountList() {
        return accountList;
    }


}

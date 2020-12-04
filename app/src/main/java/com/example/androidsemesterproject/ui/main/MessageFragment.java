package com.example.androidsemesterproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsemesterproject.R;
import com.example.androidsemesterproject.model.email.EMail;
import com.example.androidsemesterproject.model.message.Message;
import com.example.androidsemesterproject.viewmodel.AccountAdapter;
import com.example.androidsemesterproject.viewmodel.AccountViewModel;
import com.example.androidsemesterproject.viewmodel.MessageAdapter;
import com.example.androidsemesterproject.viewmodel.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {
    RecyclerView messageList;
    MessageAdapter messageAdapter;
    MessageViewModel messageViewModel;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.message_fragment, container, false);

        messageViewModel =  new ViewModelProvider(getActivity()).get(MessageViewModel.class);
        messageList = (RecyclerView) root.findViewById(R.id.message_rv);
        System.out.println("I came here");
        messageList.hasFixedSize();
        messageList.setLayoutManager(new LinearLayoutManager(getContext()));
         List<Message> messages = messageViewModel.getAllMessages().getValue();
        messageAdapter = new MessageAdapter(messages,messageViewModel);
        messageList.setAdapter(messageAdapter);

        messageViewModel.getAllMessages().observe(getActivity(), new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages1) {
                messages1= messageViewModel.getAllMessages().getValue();
                messageAdapter = new MessageAdapter(messages1,messageViewModel);
                messageList.setAdapter(messageAdapter) ;           }
        });



        return root;
    }
}
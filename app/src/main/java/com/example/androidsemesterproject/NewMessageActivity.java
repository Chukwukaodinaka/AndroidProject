package com.example.androidsemesterproject;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidsemesterproject.model.email.EMail;
import com.example.androidsemesterproject.model.message.Message;
import com.example.androidsemesterproject.viewmodel.AccountViewModel;
import com.example.androidsemesterproject.viewmodel.MessageViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalTime;
import java.util.List;

public class NewMessageActivity extends AppCompatActivity {

    private Spinner drop_down;
    private EditText sendTo;
    private EditText title;
    private EditText message;
    private Spinner time;
    MessageViewModel messageViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        drop_down = findViewById(R.id.dropdown_menu);
        sendTo = findViewById(R.id.sendTo_value);
        title = findViewById(R.id.title_value);
        message = findViewById(R.id.message_send_value);
        time = findViewById(R.id.editTextTime);
        AccountViewModel viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        List<EMail> drop_downarray =viewModel.getAllAccount().getValue();

        String[] arraySpinner = new String[drop_downarray.size()];
        for (int i =0; i < drop_downarray.size();i++)
        {
            arraySpinner[i] = drop_downarray.get(i).fullAddress();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drop_down.setAdapter(adapter);



    }


    public void onAddButtonAction(View view) {

        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        String text = drop_down.getSelectedItem().toString().trim();
        String to = sendTo.getText().toString();
        String message_ = message.getText().toString();
        String title_string = title.getText().toString();
        String time_ = time.getSelectedItem().toString();



        if (!Patterns.EMAIL_ADDRESS.matcher(title_string).matches()) {
            title.setError("Valid Recipient required");
            title.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            Toast.makeText(NewMessageActivity.this,"please choose E-mail",Toast.LENGTH_LONG).show();
            return;
        }

        if (to.isEmpty()) {


            sendTo.setError("Title required");
            sendTo.requestFocus();
            return;
        }

        if (message_.isEmpty()) {
            message.setError("Message required");
            message.requestFocus();
            return;
        }


        Message message = new Message(text,title_string,to,message_,time_,false);
        messageViewModel.addMessage(message);
        this.finish();

    }




}

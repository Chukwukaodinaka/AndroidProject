package com.example.androidsemesterproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidsemesterproject.ui.main.AddFragment;
import com.example.androidsemesterproject.viewmodel.AccountViewModel;

public class AccountListActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountsinglerow);
        textView = findViewById(R.id.email);
    }



    public void onAddAccountButtonAction(View view)
    {
        Class destination = CreateAccountActivity.class;
        Intent intent = new Intent(this,destination);
        startActivity(intent);

    }


}

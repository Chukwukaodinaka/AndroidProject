package com.example.androidsemesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidsemesterproject.viewmodel.AccountViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {

    EditText text;
    EditText pass_text;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        text = findViewById(R.id.emailName);
        pass_text = findViewById(R.id.password);
        progressBar = findViewById(R.id.progress);
        mAuth = FirebaseAuth.getInstance();

    }





    public void onCreateAccountButtonAction(View view) {
        if(text.getText().toString() == null || pass_text.getText().toString()== null || text.getText().toString().equals("") || pass_text.getText().toString().equals("")){
            Toast.makeText(CreateAccountActivity.this,"values are not set", Toast.LENGTH_LONG).show();
        }
        else {

            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(text.getText().toString(), pass_text.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {

                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(CreateAccountActivity.this, "Registered successfully Please verify email", Toast.LENGTH_LONG).show();
                                    AccountViewModel accountViewModel = new AccountViewModel(CreateAccountActivity.this.getApplication());
                                    accountViewModel.addAccount(text.getText().toString(),pass_text.getText().toString());
                                    fin();
                                } else {
                                    Toast.makeText(CreateAccountActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(CreateAccountActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
    void fin()
    {
        this.finish();
    }




}
package com.example.androidsemesterproject;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidsemesterproject.ui.main.AddFragment;
import com.example.androidsemesterproject.ui.main.MessageFragment;
import com.example.androidsemesterproject.viewmodel.AccountViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidsemesterproject.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    EditText text;
    TextView addAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        text = findViewById(R.id.emailName);
        addAcc = findViewById(R.id.email);
        tabs.setupWithViewPager(viewPager);
    }

    public void onfabClickaction(View view) {
        Class destination = NewMessageActivity.class;
        Intent intent = new Intent(this,destination);
        startActivity(intent);
    }

    public void onAddAccountButtonAction(View view)
    {
        Class destination = CreateAccountActivity.class;
        Intent intent = new Intent(this,destination);
        startActivity(intent);
    }




}
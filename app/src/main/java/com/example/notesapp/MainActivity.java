package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBaseHelper = new DataBaseHelper(this);

        binding.login.setOnClickListener(v -> {
            String mail = binding.loginEmail.getText().toString();
            String pw = binding.loginPassword.getText().toString();

            if (mail.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean checkAccount = dataBaseHelper.checkEmailPassword(mail, pw);
                if (Boolean.TRUE.equals(checkAccount)) {
                    Log.d("Access", "Successfully");
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Invalid Account", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, signup_activity.class);
            startActivity(intent);
        });
    }
}
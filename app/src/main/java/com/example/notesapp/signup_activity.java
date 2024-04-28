package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.databinding.ActivitySignupBinding;

public class signup_activity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBaseHelper = new DataBaseHelper(this);

        binding.signup.setOnClickListener(v -> {

            String email = binding.signupEmail.getText().toString();
            String password = binding.signupPassword.getText().toString();
            String confirmPassword = binding.signupCfPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(signup_activity.this,
                        "Fill your Information", Toast.LENGTH_SHORT).show();

            } else if (confirmPassword.equals(password)) {
                boolean checkEmailUser = dataBaseHelper.checkEmail(email);

                if (!checkEmailUser) {
                    Boolean insert = dataBaseHelper.insertData(email, password);

                    if (Boolean.TRUE.equals(insert)) {
                        Toast.makeText(this, "Successfully Sign up", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(signup_activity.this, MainActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(this, "Signup fail", Toast.LENGTH_SHORT).show();
                        
                } else
                    Toast.makeText(this, "Email is already Exist", Toast.LENGTH_SHORT).show();
                
            } else {
                Toast.makeText(this, "Your Confirm Password is Incorrect", Toast.LENGTH_SHORT).show();

            }
        });

        binding.goBack.setOnClickListener(v -> {
            Intent intent = new Intent(signup_activity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {

    private EditText email;
    private RelativeLayout nextButton;
    private TextView goBack;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.check_email);
        nextButton = findViewById(R.id.next_button);
        goBack = findViewById(R.id.back_button);


        firebaseAuth = FirebaseAuth.getInstance();

        email.setOnClickListener(v -> {
            String mail = email.getText().toString();

            if (mail.isEmpty()) {
                Toast.makeText(Forgot_password.this,
                        "You haven't enter your Email", Toast.LENGTH_SHORT).show();
            }
        });

        nextButton.setOnClickListener(v -> {
            //next activity
            String mail = email.getText().toString();

            if (mail.isEmpty()) {
                Toast.makeText(Forgot_password.this,
                        "You haven't enter Email", Toast.LENGTH_SHORT).show();

            } else {
                firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        Toast.makeText(this,
                                "You can recover your Password using Email", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
            }
        });

        goBack.setOnClickListener(v -> {
            Intent intent = new Intent(Forgot_password.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
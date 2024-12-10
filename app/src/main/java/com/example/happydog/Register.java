package com.example.happydog;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText etUsername, etEmail, etPassword;
    Button btnSignUp;
    TextView tvSignIn;
    DB_operations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.txttUsername2);
        etEmail = findViewById(R.id.txtEmail);
        etPassword = findViewById(R.id.txtpassword2);
        btnSignUp = findViewById(R.id.btnSignup2);
        tvSignIn = findViewById(R.id.textViewSignup1);
        dbOperations = new DB_operations(this);

        btnSignUp.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                dbOperations.addUser(username, email, password);
                Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this, Login.class));
                finish();
            }
        });

        tvSignIn.setOnClickListener(v -> startActivity(new Intent(Register.this, Login.class)));
    }
}

package com.example.happydog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button btnSignIn;
    DB_operations db;
    TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.textusername);
        txtPassword = findViewById(R.id.textpassword);
        btnSignIn = findViewById(R.id.btnlogin);
        tvSignUp = findViewById(R.id.textviewlogin);

        db = new DB_operations(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txtUsername.getText().toString().trim();
                String pass = txtPassword.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(Login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Check user credentials
                    boolean result = db.checkUser(user, pass);

                    if (result) {
                        // Login successful, navigate to Home_Page
                        Intent intent = new Intent(Login.this, Homepage.class);
                        intent.putExtra("username", user);
                        startActivity(intent);
                        clearFields();
                    } else {
                        // Login failed, display error message
                        Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    private void openRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}

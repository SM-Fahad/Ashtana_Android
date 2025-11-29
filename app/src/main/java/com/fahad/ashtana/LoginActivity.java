package com.fahad.ashtana;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        // Remove progress bar reference since it's not mandatory
    }

    private void setupClickListeners() {
        loginButton.setOnClickListener(v -> attemptLogin());

        signUpButton.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "Navigate to Sign Up", Toast.LENGTH_SHORT).show();
            // Implement sign up navigation
        });
    }

    private void attemptLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Disable login button during login process
        loginButton.setEnabled(false);
        loginButton.setText("LOGGING IN...");

        // Simulate login process
        new android.os.Handler().postDelayed(() -> {
            // Re-enable login button
            loginButton.setEnabled(true);
            loginButton.setText("LOGIN");

            // For demo purposes, always succeed
            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
            finish(); // Close login activity and return to main
        }, 1500);
    }
}
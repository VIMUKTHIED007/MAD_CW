package com.example.myapplication1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateaccountActivity extends AppCompatActivity {

    private EditText nameCreate, email1Create, passwordCreate, paswordConfirm;
    private Button btnregister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_createaccount);




        mAuth = FirebaseAuth.getInstance();

        nameCreate = findViewById(R.id.nameCreate);
        email1Create = findViewById(R.id.email1Create);
        passwordCreate = findViewById(R.id.passwordCreate);
        paswordConfirm = findViewById(R.id.paswordConfirm);
        btnregister = findViewById(R.id.btnregister);

        btnregister.setOnClickListener(v -> registerUser());
    }


    private void registerUser() {
        String email = email1Create.getText().toString().trim();
        String password = passwordCreate.getText().toString().trim();
        String confirmPassword = paswordConfirm.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Send verification email
                            user.sendEmailVerification()
                                    .addOnCompleteListener(emailTask -> {
                                        if (emailTask.isSuccessful()) {
                                            Toast.makeText(this, "Registration successful! Please check your email to verify.", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            // Navigate to login screen after registration
                            startActivity(new Intent(CreateaccountActivity.this, LoginActivity.class));
                            finish();
                        }
                    } else {
                        Toast.makeText(this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

package com.millenialzdev.logindanregistervolleymysql.Login_dan_Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.millenialzdev.logindanregistervolleymysql.Admin.Admin;
import com.millenialzdev.logindanregistervolleymysql.MainActivity;
import com.millenialzdev.logindanregistervolleymysql.R;

public class Login extends AppCompatActivity {

    private Button btnRegister, btnLogin;
    private EditText etUsernameOrEmail, etPassword;
    private DatabaseReference database;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_login);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);

        // Check if user is already logged in
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            navigateToMainActivity();
        }

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        etUsernameOrEmail = findViewById(R.id.etUsernameOrEmail);
        etPassword = findViewById(R.id.etPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(), Register.class);
                startActivity(register);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameOrEmail = etUsernameOrEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                database = FirebaseDatabase.getInstance().getReference("users");

                if (usernameOrEmail.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Isi username & password", Toast.LENGTH_SHORT).show();
                } else if (usernameOrEmail.equals("admin") && password.equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Login Admin", Toast.LENGTH_SHORT).show();
                    Intent masuk = new Intent(getApplicationContext(), Admin.class);
                    startActivity(masuk);
                } else {
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean loginSuccessful = false;

                            // Check login with NIM or email
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                String storedUsername = userSnapshot.getKey();
                                String storedEmail = userSnapshot.child("email").getValue(String.class);
                                String storedPassword = userSnapshot.child("password").getValue(String.class);

                                if ((storedUsername != null && storedUsername.equals(usernameOrEmail) ||
                                        (storedEmail != null && storedEmail.equals(usernameOrEmail))) &&
                                        (storedPassword != null && storedPassword.equals(password))) {
                                    loginSuccessful = true;
                                    break;
                                }
                            }

                            if (loginSuccessful) {
                                // Save login status to SharedPreferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isLoggedIn", true);
                                editor.apply();

                                Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                navigateToMainActivity();
                            } else {
                                Toast.makeText(getApplicationContext(), "Username / Password salah", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void navigateToMainActivity() {
        Intent masuk = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(masuk);
        finish(); // Close the login activity
    }
}

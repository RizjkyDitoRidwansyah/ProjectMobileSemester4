package com.millenialzdev.logindanregistervolleymysql.Login_dan_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.millenialzdev.logindanregistervolleymysql.Login_dan_Register.Login;
import com.millenialzdev.logindanregistervolleymysql.R;

public class Register extends AppCompatActivity {

    private EditText etUsername, etNIM , etEmail, etPassword;
    private Button btnRegister;
    private ImageView tvKeluar;

    private DatabaseReference database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etNIM = findViewById(R.id.etNIM);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvKeluar = findViewById(R.id.tvKeluar);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://login-dan-register-ba297-default-rtdb.firebaseio.com/");

        tvKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString().trim();
                final String NIM = etNIM.getText().toString().toString().trim();
                final String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || NIM.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ada data yang masih kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if username already exists
                    DatabaseReference usernameRef = FirebaseDatabase.getInstance().getReference("users").child(NIM);
                    usernameRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Username already exists
                                Toast.makeText(getApplicationContext(), "NIM sudah dipakai!", Toast.LENGTH_SHORT).show();
                            } else {
                                // Username is available, proceed with registration
                                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(NIM);
                                userRef.child("username").setValue(username);
                                userRef.child("NIM").setValue(NIM);
                                userRef.child("email").setValue(email);
                                userRef.child("password").setValue(password);

                                Toast.makeText(getApplicationContext(), "Register Berhasil", Toast.LENGTH_SHORT).show();
                                Intent registerIntent = new Intent(getApplicationContext(), Login.class);
                                startActivity(registerIntent);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}
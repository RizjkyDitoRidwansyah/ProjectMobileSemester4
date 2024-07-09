package com.millenialzdev.logindanregistervolleymysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.millenialzdev.logindanregistervolleymysql.Admin.Admin;

import java.util.HashMap;

public class EditData extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etNIM;
    private Button btnSimpan, btnBATAL;
    private String username, email, passsword, NIM;

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");

    @Override
    public void onBackPressed() {
        // Do nothing or handle as per your requirement
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_edit_data);

        etNIM = findViewById(R.id.etNIM);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnBATAL = findViewById(R.id.btnBatal);

        if (getIntent().hasExtra("username") && getIntent().hasExtra("email") && getIntent().hasExtra("password") && getIntent().hasExtra("NIM")) {
            username = getIntent().getStringExtra("username");
            NIM = getIntent().getStringExtra("NIM");
            email = getIntent().getStringExtra("email");
            passsword = getIntent().getStringExtra("password");
        }

        etNIM.setText(NIM);
        etUsername.setText(username);
        etEmail.setText(email);
        etPassword.setText(passsword);

        btnBATAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NIMBaru = etNIM.getText().toString();
                String usernameBaru = etUsername.getText().toString();
                String emailBaru = etEmail.getText().toString();
                String passwordBaru = etPassword.getText().toString();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("NIM", NIMBaru);
                hashMap.put("username", usernameBaru);
                hashMap.put("email", emailBaru);
                hashMap.put("password", passwordBaru);

                database.child(NIM).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Admin.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}

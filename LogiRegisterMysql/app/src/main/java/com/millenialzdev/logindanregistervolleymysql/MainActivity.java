package com.millenialzdev.logindanregistervolleymysql;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.millenialzdev.logindanregistervolleymysql.Login_dan_Register.Login;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        btnLogout = findViewById(R.id.btnLogout);

        Toast.makeText(MainActivity.this, "Anda sudah login", Toast.LENGTH_SHORT).show();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the login status
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                // Navigate to the login activity
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish(); // Close the main activity
            }
        });
    }
}

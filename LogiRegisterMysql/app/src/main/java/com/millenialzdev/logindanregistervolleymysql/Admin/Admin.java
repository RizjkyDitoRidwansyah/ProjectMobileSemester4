package com.millenialzdev.logindanregistervolleymysql.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.millenialzdev.logindanregistervolleymysql.Entitas.User;
import com.millenialzdev.logindanregistervolleymysql.Login_dan_Register.Login;
import com.millenialzdev.logindanregistervolleymysql.R;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    private ImageView tvKeluar;

    private RecyclerView tvUser;

    private DatabaseReference database;

    private UserAdapter adapter;

    private ArrayList<User> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_admin);

        tvKeluar = findViewById(R.id.tvKeluar);
        tvUser = findViewById(R.id.tvUser);

        tvKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        database = FirebaseDatabase.getInstance().getReference("users");

        tvUser = findViewById(R.id.tvUser);
        tvUser.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        tvUser.setLayoutManager(layoutManager);
        tvUser.setItemAnimator(new DefaultItemAnimator());

        tampilData();
    }

    private void tampilData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList = new ArrayList<>();

                for (DataSnapshot item : snapshot.getChildren()) {
                    User user = item.getValue(User.class);
                    if (user != null) {
                        arrayList.add(user);
                    }
                }
                adapter = new UserAdapter(arrayList, Admin.this);
                tvUser.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Log atau tampilkan pesan error jika diperlukan
            }
        });

    }
}

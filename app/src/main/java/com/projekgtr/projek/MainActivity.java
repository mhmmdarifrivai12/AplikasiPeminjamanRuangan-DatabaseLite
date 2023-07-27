package com.projekgtr.projek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        logout = (Button)findViewById(R.id.btn_logout);


        Boolean checkSession = db.checkSession("ada");
        if (checkSession == false) {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

        // logout
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Boolean updtSession = db.upgradeSession("kosong", 1);
                if (updtSession == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

        LinearLayout card1 = findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tindakan yang ingin dilakukan saat layout diklik
                // Misalnya, buka MainActivity peminjaman
                Intent intent = new Intent(MainActivity.this, Peminjaman.class);
                startActivity(intent);
            }
        });

        LinearLayout card2 = findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tindakan yang ingin dilakukan saat layout diklik
                // Misalnya, buka MainActivity peminjaman
                Intent intent = new Intent(MainActivity.this, JadwalPeminjaman.class);
                startActivity(intent);
            }
        });

        LinearLayout card3 = findViewById(R.id.card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tindakan yang ingin dilakukan saat layout diklik
                // Misalnya, buka MainActivity peminjaman
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });

    }
}

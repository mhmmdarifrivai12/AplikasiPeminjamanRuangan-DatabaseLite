package com.projekgtr.projek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class Peminjaman extends AppCompatActivity {

    private EditText namaAcaraEditText;
    private EditText namaEditText;
    private EditText nomorHandphoneEditText;
    private Spinner gedungSpinner;
    private EditText tanggalEditText;
    private EditText jamEditText;
    private Button kirimButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman);

        databaseHelper = new DatabaseHelper(this);

        namaAcaraEditText = findViewById(R.id.namaAcaraEditText);
        namaEditText = findViewById(R.id.namaEditText);
        nomorHandphoneEditText = findViewById(R.id.nomorHandphoneEditText);
        gedungSpinner = findViewById(R.id.gedungSpinner);
        tanggalEditText = findViewById(R.id.tanggalEditText);
        jamEditText = findViewById(R.id.jamEditText);
        kirimButton = findViewById(R.id.btn_peminjaman);
        kirimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanPeminjaman();
            }
        });
    }

    private void simpanPeminjaman() {
        String namaAcara = namaAcaraEditText.getText().toString();
        String nama = namaEditText.getText().toString();
        String nomorHandphone = nomorHandphoneEditText.getText().toString();
        String gedung = gedungSpinner.getSelectedItem().toString();
        String tanggal = tanggalEditText.getText().toString();
        String jam = jamEditText.getText().toString();

        if (namaAcara.isEmpty() || nama.isEmpty() || nomorHandphone.isEmpty() || tanggal.isEmpty() || jam.isEmpty()) {
            Toast.makeText(this, "Harap lengkapi semua data", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean berhasil = databaseHelper.insertPeminjaman(namaAcara, nama, nomorHandphone, gedung, tanggal, jam);
        if (berhasil) {
            Toast.makeText(this, "Peminjaman berhasil disimpan", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Gagal menyimpan peminjaman", Toast.LENGTH_SHORT).show();
        }
    }
}
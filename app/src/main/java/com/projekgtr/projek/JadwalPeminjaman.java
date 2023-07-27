package com.projekgtr.projek;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class JadwalPeminjaman extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_peminjaman);

        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);

        // Ambil semua data peminjaman dari database
        List<String> peminjamanList = getAllPeminjaman();

        // Buat adapter untuk ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, peminjamanList);

        // Set adapter ke ListView
        listView.setAdapter(adapter);
    }

    private List<String> getAllPeminjaman() {
        List<String> peminjamanList = new ArrayList<>();

        // Buka database dalam mode baca
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Ambil semua data peminjaman
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_PEMINJAMAN, null);
        if (cursor.moveToFirst()) {
            do {
                // Ambil nilai kolom yang diinginkan (misalnya COLUMN_NAMA_ACARA, COLUMN_TANGGAL)
                @SuppressLint("Range") String nama = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA));
                @SuppressLint("Range") String namaAcara = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA_ACARA));
                @SuppressLint("Range") String notelp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMOR_HANDPHONE));
                @SuppressLint("Range") String gedung = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_GEDUNG));
                @SuppressLint("Range") String tanggal = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TANGGAL));
                @SuppressLint("Range") String jam = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_JAM));


                // Buat teks yang akan ditampilkan di ListView
                String peminjamanText = "Nama Peminjam : " + nama + "\nAcara : " + namaAcara + "\nNomor Handphone : " + notelp + "\nGedung : " + gedung + "\nTanggal : " + tanggal + "\nJam : " + jam + "WIB";

                // Tambahkan teks ke daftar peminjaman
                peminjamanList.add(peminjamanText);
            } while (cursor.moveToNext());
        }

        // Tutup cursor dan database
        cursor.close();
        db.close();

        return peminjamanList;
    }
}

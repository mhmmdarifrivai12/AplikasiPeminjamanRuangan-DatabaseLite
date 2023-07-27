package com.projekgtr.projek;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private TextView txtUsername;
    private TextView txtPassword;
    private TextView txtNotelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databaseHelper = new DatabaseHelper(this);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtNotelp = findViewById(R.id.txtNotelp);

        // Retrieve account information from the database
        String username = getAccountUsername();
        String password = getAccountPassword();
        String noTelp = getAccountNotelp();

        // Display the account information in the TextView elements
        txtUsername.setText("" + username);
        txtPassword.setText("" + password);
        txtNotelp.setText("" + noTelp);
    }

    @SuppressLint("Range")
    private String getAccountUsername() {
        // Assuming you have a method to retrieve the account username from the database
        // Modify this code according to your implementation
        String username = "";

        // Retrieve the username from the database
        // Example:
        Cursor cursor = databaseHelper.getReadableDatabase().query(
                DatabaseHelper.TABLE_USER,
                new String[]{DatabaseHelper.COLUMN_USERNAME},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
        }

        cursor.close();

        return username;
    }

    @SuppressLint("Range")
    private String getAccountNotelp() {
        // Assuming you have a method to retrieve the account notelp from the database
        // Modify this code according to your implementation
        String noTelp = "";

        // Retrieve the notelp from the database
        // Example:
        Cursor cursor = databaseHelper.getReadableDatabase().query(
                DatabaseHelper.TABLE_USER,
                new String[]{DatabaseHelper.COLUMN_TELP},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            noTelp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TELP));
        }

        cursor.close();

        return noTelp;
    }

    @SuppressLint("Range")
    private String getAccountPassword() {
        // Assuming you have a method to retrieve the account password from the database
        // Modify this code according to your implementation
        String password = "";

        // Retrieve the password from the database
        // Example:
        Cursor cursor = databaseHelper.getReadableDatabase().query(
                DatabaseHelper.TABLE_USER,
                new String[]{DatabaseHelper.COLUMN_PASSWORD},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD));
        }

        cursor.close();

        return password;
    }
}

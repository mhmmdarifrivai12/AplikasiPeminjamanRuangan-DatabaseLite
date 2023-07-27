package com.projekgtr.projek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_peminjaman";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_SESSION = "session";
    private static final String COLUMN_ID_SESSION = "id";
    private static final String COLUMN_LOGIN = "login";

    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID_USER = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_TELP = "notelp";


    public static final String TABLE_PEMINJAMAN = "peminjaman";
    public static final String COLUMN_ID_PEMINJAMAN = "_id";
    public static final String COLUMN_NAMA_ACARA = "nama_acara";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_NOMOR_HANDPHONE = "nomor_handphone";
    public static final String COLUMN_GEDUNG = "gedung";
    public static final String COLUMN_TANGGAL = "tanggal";
    public static final String COLUMN_JAM = "jam";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSessionTableQuery = "CREATE TABLE " + TABLE_SESSION + "(" +
                COLUMN_ID_SESSION + " INTEGER PRIMARY KEY, " +
                COLUMN_LOGIN + " TEXT)";
        db.execSQL(createSessionTableQuery);

        String createUserTableQuery = "CREATE TABLE " + TABLE_USER + "(" +
                COLUMN_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_TELP + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUserTableQuery);

        String createPeminjamanTableQuery = "CREATE TABLE " + TABLE_PEMINJAMAN + "(" +
                COLUMN_ID_PEMINJAMAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAMA_ACARA + " TEXT, " +
                COLUMN_NAMA + " TEXT, " +
                COLUMN_NOMOR_HANDPHONE + " TEXT, " +
                COLUMN_GEDUNG + " TEXT, " +
                COLUMN_TANGGAL + " TEXT, " +
                COLUMN_JAM + " TEXT)";
        db.execSQL(createPeminjamanTableQuery);

        db.execSQL("INSERT INTO " + TABLE_SESSION + "(" + COLUMN_ID_SESSION + ", " + COLUMN_LOGIN + ") VALUES(1, 'kosong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEMINJAMAN);
        onCreate(db);
    }

    // Check session
    public Boolean checkSession(String sessionValues) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SESSION + " WHERE " + COLUMN_LOGIN + " = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    // Upgrade session
    public Boolean upgradeSession(String sessionValues, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOGIN, sessionValues);
        int update = db.update(TABLE_SESSION, contentValues, COLUMN_ID_SESSION + " = ?", new String[]{String.valueOf(id)});
        return update != 0;
    }

    // Insert user
    public Boolean insertUser(String username, String password, String notelp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_TELP, notelp);
        contentValues.put(COLUMN_PASSWORD, password);
        long insert = db.insert(TABLE_USER, null, contentValues);
        return insert != -1;
    }

    // Check login
    public Boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    // Insert peminjaman
    public Boolean insertPeminjaman(String namaAcara, String nama, String nomorHandphone, String gedung, String tanggal, String jam) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMA_ACARA, namaAcara);
        contentValues.put(COLUMN_NAMA, nama);
        contentValues.put(COLUMN_NOMOR_HANDPHONE, nomorHandphone);
        contentValues.put(COLUMN_GEDUNG, gedung);
        contentValues.put(COLUMN_TANGGAL, tanggal);
        contentValues.put(COLUMN_JAM, jam);
        long insert = db.insert(TABLE_PEMINJAMAN, null, contentValues);
        return insert != -1;
    }

    // Mendapatkan semua data peminjaman
    public Cursor getAllPeminjaman() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PEMINJAMAN, null, null, null, null, null, null);
    }

}

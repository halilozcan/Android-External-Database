package com.halilozcan.externaldatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ExternalDatabaseOpenHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "externaldatabase.sqlite";
    private static final int DATABASE_VERSION = 1;

    private static ExternalDatabaseOpenHelper sDatabaseInstance;

    private ExternalDatabaseOpenHelper(@Nullable Context context) {
        super(new ExternalDatabaseContext(context), DATABASE_NAME, null, DATABASE_VERSION);
    }

    static synchronized ExternalDatabaseOpenHelper newInstance(Context context) {
        if (sDatabaseInstance == null) {
            sDatabaseInstance = new ExternalDatabaseOpenHelper(context);
        }
        return sDatabaseInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS person" +
                "( name VARCHAR," +
                "  age INTEGER )";
        db.execSQL(createTableQuery);
    }

    void insertPerson() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String insertPersonQuery = "INSERT INTO person (name,age) VALUES ('Halil',25)";
        sqLiteDatabase.execSQL(insertPersonQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
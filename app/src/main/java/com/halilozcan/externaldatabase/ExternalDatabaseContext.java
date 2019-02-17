package com.halilozcan.externaldatabase;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

public class ExternalDatabaseContext extends ContextWrapper {

    public ExternalDatabaseContext(Context base) {
        super(base);
    }

    @Override
    public File getDatabasePath(String name) {
        String databaseFile = new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PODCASTS) + "/" +
                        ExternalDatabaseOpenHelper.DATABASE_NAME)
                .getAbsolutePath();

        File databaseCreateFile = new File(databaseFile);

        if (!databaseCreateFile.getParentFile().exists()) {
            databaseCreateFile.getParentFile().mkdirs();
        }
        return databaseCreateFile;
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(
            String name,
            int mode,
            SQLiteDatabase.CursorFactory factory,
            DatabaseErrorHandler errorHandler) {
        return openOrCreateDatabase(name, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(
            String name,
            int mode,
            SQLiteDatabase.CursorFactory factory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
    }
}
package com.halilozcan.externaldatabase;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class ExternalDatabaseActivity extends AppCompatActivity {

    private static final int WRITE_PERMISSION_REQUEST_CODE = 1967;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_database);

        if (ActivityCompat.checkSelfPermission(
                getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            ExternalDatabaseOpenHelper externalDatabaseOpenHelper =
                    ExternalDatabaseOpenHelper.newInstance(getApplicationContext());

            externalDatabaseOpenHelper.insertPerson();
            externalDatabaseOpenHelper.close();
        } else {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    ExternalDatabaseOpenHelper externalDatabaseOpenHelper =
                            ExternalDatabaseOpenHelper.newInstance(getApplicationContext());
                    externalDatabaseOpenHelper.insertPerson();
                    externalDatabaseOpenHelper.close();
                }
        }
    }
}
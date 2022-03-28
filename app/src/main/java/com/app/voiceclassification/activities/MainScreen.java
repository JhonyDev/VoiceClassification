package com.app.voiceclassification.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.voiceclassification.R;
import com.app.voiceclassification.utils.SharedPrefUtils;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    public void getPrediction(View view) {
        startActivity(new Intent(this, FormPrediction.class));
    }

    public void history(View view) {
        startActivity(new Intent(this, HistoryActivity.class));
    }

    public void logout(View view) {
        SharedPrefUtils.setToken(this, "");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void profileSettings(View view) {
        startActivity(new Intent(this, ProfileManagementActivity.class));
    }
}
package com.app.voiceclassification.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.voiceclassification.R;
import com.app.voiceclassification.sigletons.HistorySingleton;

public class HistoryDetail extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        textView = findViewById(R.id.details);

        textView.setText(HistorySingleton.getInstance().toString());

    }

}
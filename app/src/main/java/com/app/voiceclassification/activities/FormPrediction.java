package com.app.voiceclassification.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.voiceclassification.R;
import com.app.voiceclassification.mvvm.MvvmUtils;
import com.app.voiceclassification.mvvm.capsules.response.PredicationRespCapsule;
import com.app.voiceclassification.mvvm.mapping_utils.GenericCall;
import com.app.voiceclassification.mvvm.mapping_utils.GenericResponse;
import com.app.voiceclassification.utils.AudioRecorder;
import com.app.voiceclassification.utils.DialogUtils;
import com.app.voiceclassification.utils.SharedPrefUtils;
import com.app.voiceclassification.utils.Utils;

import java.io.File;


public class FormPrediction extends AppCompatActivity {

    Dialog loadingDialog;

    private ImageButton recordBtn;
    private TextView filenameText;
    private Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_prediction);
        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);
        recordBtn = findViewById(R.id.record_btn);
        timer = findViewById(R.id.record_timer);
        filenameText = findViewById(R.id.record_filename);
    }

    public void back(View view) {
        finish();
    }


    public void record(View view) {
        AudioRecorder.startRecording(this, filenameText, timer, recordBtn);
    }

    public void predict(View view) {
        if (AudioRecorder.lastFilePath.isEmpty())
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        else
            uploadFile();
    }

    private void uploadFile() {
        if (AudioRecorder.seconds < 15 | AudioRecorder.seconds > 46) {
            Toast.makeText(this, "Audio file cannot be more less than 15 seconds", Toast.LENGTH_SHORT).show();
            return;
        }

        loadingDialog.show();
        new GenericCall<>(MvvmUtils.getNcs().postPrediction(SharedPrefUtils.getToken(this),
                Utils.fileRequest(new File(AudioRecorder.lastFilePath),
                        "audio")))
                .getMutableLiveData().observe(this, this::initResponse);
    }

    private void initResponse(GenericResponse<PredicationRespCapsule> predicationRespCapsuleGenericResponse) {
        loadingDialog.dismiss();
        if (predicationRespCapsuleGenericResponse.isSuccessful()) {
            startActivity(new Intent(this, HistoryActivity.class));
            finish();
        } else {
            Toast.makeText(this, "An error occurred :" + predicationRespCapsuleGenericResponse.getResponseCode()
                    , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        AudioRecorder.lastFilePath = "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioRecorder.lastFilePath = "";
    }

}
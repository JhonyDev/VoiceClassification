package com.app.voiceclassification.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.app.voiceclassification.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AudioRecorder {
    public static String lastFilePath = "";
    private static boolean isRecording = false;
    private static MediaRecorder mediaRecorder;
    private static String recordFile;

    public static void startRecording(Activity context, TextView filenameText, Chronometer timer, ImageButton recordBtn) {
        if (isRecording) {
            //Stop Recording
            stopRecording(filenameText, timer);

            // Change button image and set Recording state to false
            recordBtn.setImageDrawable(context.getDrawable(R.drawable.record_btn_stopped));
            isRecording = false;
        } else {
            //Check permission to record audio
            if (checkPermissions(context)) {
                //Start Recording


                //Start timer from 0
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();

                //Get app external directory path
                String recordPath = context.getExternalFilesDir("/").getAbsolutePath();

                //Get current date and time
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
                Date now = new Date();

                //initialize filename variable with date and time at the end to ensure the new file wont overwrite previous file
                recordFile = "Recording_" + formatter.format(now) + ".3gp";

                filenameText.setText("Recording, File Name : " + recordFile);

                //Setup Media Recorder for recording
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setOutputFile(recordPath + "/" + recordFile);
                lastFilePath = recordPath + "/" + recordFile;
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                try {
                    mediaRecorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Start Recording
                mediaRecorder.start();


                // Change button image and set Recording state to false
                recordBtn.setImageDrawable(context.getDrawable(R.drawable.record_btn_recording));
                isRecording = true;
            }
        }


    }

    public static void stopRecording(TextView filenameText, Chronometer timer) {
        //Stop Timer, very obvious
        timer.stop();

        //Change text on page to file saved
        filenameText.setText("Recording Stopped, File Saved : " + recordFile);

        //Stop media recorder and set it to null for further use to record new audio
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    public static boolean checkPermissions(Activity context) {
        //Check permission
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            //Permission Granted
            return true;
        } else {
            //Permission not granted, ask for permission
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.RECORD_AUDIO}, 21);
            return false;
        }
    }

}

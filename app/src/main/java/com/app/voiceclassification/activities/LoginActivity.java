package com.app.voiceclassification.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.voiceclassification.R;
import com.app.voiceclassification.info.Info;
import com.app.voiceclassification.mvvm.MvvmUtils;
import com.app.voiceclassification.mvvm.capsules.request.PostLoginPojo;
import com.app.voiceclassification.mvvm.capsules.response.RegResponse;
import com.app.voiceclassification.mvvm.mapping_utils.GenericCall;
import com.app.voiceclassification.mvvm.mapping_utils.GenericResponse;
import com.app.voiceclassification.utils.DialogUtils;
import com.app.voiceclassification.utils.SharedPrefUtils;
import com.app.voiceclassification.utils.Utils;

public class LoginActivity extends AppCompatActivity implements Info {
    public static Activity context;
    EditText etEmail;
    EditText etPassword;
    String strEtEmail;
    String strEtPassword;
    boolean isPassVisible = false;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_pass);

        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);

        Log.i(TAG, "onCreate: " + SharedPrefUtils.getToken(this));

//        Check if token already exist in shared preferences
        if (!SharedPrefUtils.isTokenEmpty(this)) {
            startActivity(new Intent(this, MainScreen.class));
            finish();
        }

    }

    public void signUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void showPassword(View view) {
        if (!isPassVisible) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isPassVisible = true;
        } else {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isPassVisible = false;
        }
    }

    private void castStrings() {
//        Casting strings from editText to variables
        strEtEmail = etEmail.getText().toString();
        strEtPassword = etPassword.getText().toString();
    }

    private boolean isEverythingValid() {
//        Check whether corresponding fields are empty or not
        if (!Utils.validEt(etEmail, strEtEmail))
            return false;
        return Utils.validEt(etPassword, strEtPassword);
    }

    public void Login(View view) {
//        This method runs when login button is clicked
        castStrings();
        if (!isEverythingValid())
            return;
        initSignIn();
    }

    private void initSignIn() {
        castStrings();
        loadingDialog.show();
        Log.i(TAG, "initSignIn: ");
//        Sending login request to server
        new GenericCall<>(MvvmUtils.getNcs().postLogin(new PostLoginPojo(strEtEmail, strEtPassword)))
                .getMutableLiveData().observe(this, this::initResponse);

    }

    private void initResponse(GenericResponse<RegResponse> regResponseGenericResponse) {
//        Catching login response from  server
        Log.i(TAG, "initResponse: " + regResponseGenericResponse.getErrorMessages());
        loadingDialog.dismiss();
        if (regResponseGenericResponse.isSuccessful()) {
//            Saving access token to shared preferences
            SharedPrefUtils.setToken(this, regResponseGenericResponse.getResponse().getKey());
            Log.i(TAG, "initResponse: " + regResponseGenericResponse.getResponse().getKey());
            startActivity(new Intent(this, MainScreen.class));
            finish();
        } else {
//            Show Toast for every error message
            MvvmUtils.printErrors(this, regResponseGenericResponse);
            Toast.makeText(this, "Error occurred please check connection", Toast.LENGTH_SHORT).show();
        }
    }
}
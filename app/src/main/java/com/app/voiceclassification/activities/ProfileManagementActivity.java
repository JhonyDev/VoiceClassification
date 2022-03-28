package com.app.voiceclassification.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.app.voiceclassification.R;
import com.app.voiceclassification.mvvm.MvvmUtils;
import com.app.voiceclassification.mvvm.capsules.response.UserPojo;
import com.app.voiceclassification.mvvm.mapping_utils.GenericCall;
import com.app.voiceclassification.mvvm.mapping_utils.GenericResponse;
import com.app.voiceclassification.utils.DialogUtils;
import com.app.voiceclassification.utils.SharedPrefUtils;
import com.hbb20.CountryCodePicker;

public class ProfileManagementActivity extends AppCompatActivity {
    Dialog loadingDialog;
    UserPojo instance;
    EditText etPhone;
    EditText etUserName;
    EditText etEmail;
    EditText etFirstName;
    EditText etLastName;
    CountryCodePicker cpp;

    String strEtPhone;
    String strEtFirstName;
    String strEtLastName;

    Button btnUpdate;

    private void initViews() {
        btnUpdate = findViewById(R.id.btn_update);
        etPhone = findViewById(R.id.et_email3);
        etUserName = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        cpp = findViewById(R.id.ccp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);
        initViews();
        loadingDialog.show();
        new GenericCall<>(MvvmUtils.getNcs().getUser(SharedPrefUtils.getToken((Activity) this)))
                .getMutableLiveData().observe((LifecycleOwner) this, regResponseGenericResponse -> {
            loadingDialog.dismiss();
            if (regResponseGenericResponse.isSuccessful()) {
                instance = regResponseGenericResponse.getResponse();
                initDetails();
            } else {
                if (regResponseGenericResponse.getResponseCode() == 401) {
                    SharedPrefUtils.setToken((Activity) this, "");
                    this.startActivity(new Intent(this, LoginActivity.class));
                    ((Activity) this).finish();
                }
                instance = null;
                Toast.makeText(this, "An error occurred " + regResponseGenericResponse.getResponseCode(),
                        Toast.LENGTH_SHORT).show();
            }

        });
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strEtPhone = etPhone.getText().toString();
                if (instance == null)
                    return;


                if (strEtPhone.isEmpty()) {
                    btnUpdate.setVisibility(View.GONE);
                    return;
                }


                if (instance.getPhoneNumber() == null) {
                    btnUpdate.setVisibility(View.VISIBLE);
                    return;
                }

                if (!instance.getPhoneNumber().equals(strEtPhone))
                    btnUpdate.setVisibility(View.VISIBLE);
                else
                    btnUpdate.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strEtFirstName = etFirstName.getText().toString();
                if (instance == null)
                    return;
                if (strEtFirstName.isEmpty()) {
                    btnUpdate.setVisibility(View.GONE);
                    return;
                }
                if (instance.getFirstName() == null) {
                    btnUpdate.setVisibility(View.VISIBLE);
                    return;
                }

                if (!instance.getFirstName().equals(strEtFirstName))
                    btnUpdate.setVisibility(View.VISIBLE);
                else
                    btnUpdate.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strEtLastName = etLastName.getText().toString();
                if (instance == null)
                    return;
                if (strEtLastName.isEmpty()) {
                    btnUpdate.setVisibility(View.GONE);
                    return;
                }
                if (instance.getFirstName() == null) {
                    btnUpdate.setVisibility(View.VISIBLE);
                    return;
                }

                if (!instance.getFirstName().equals(strEtLastName))
                    btnUpdate.setVisibility(View.VISIBLE);
                else
                    btnUpdate.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void initDetails() {
        if (instance == null)
            return;
        etEmail.setText(instance.getEmail());
        etUserName.setText(instance.getUsername());

        try {
            etPhone.setText(instance.getPhoneNumber().split("-")[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        etFirstName.setText(instance.getFirstName());
        etLastName.setText(instance.getLastName());
        try {
            cpp.setCountryForPhoneCode(
                    Integer.parseInt(instance.getPhoneNumber().split("-")[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void submit(View view) {
        strEtPhone = etPhone.getText().toString();
        strEtFirstName = etFirstName.getText().toString();
        strEtLastName = etLastName.getText().toString();
        initProfileUpdate();
    }

    private void initProfileUpdate() {
        UserPojo postProfilePojo = new UserPojo(strEtFirstName, strEtLastName, instance.getUsername(),
                instance.getEmail(), cpp.getSelectedCountryCode() + "-" + strEtPhone);
        Log.i("TAG", "initProfileUpdate: " + postProfilePojo.getPhoneNumber());
        new GenericCall<>(MvvmUtils.getNcs().putProfile(SharedPrefUtils.getToken((Activity) this), postProfilePojo))
                .getMutableLiveData().observe((LifecycleOwner) this, this::initResp);
    }

    private void initResp(GenericResponse<UserPojo> userPojoGenericResponse) {
        if (userPojoGenericResponse.isSuccessful()) {
            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
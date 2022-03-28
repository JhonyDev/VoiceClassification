package com.app.voiceclassification.utils;

import android.widget.EditText;

import com.app.voiceclassification.mvvm.capsules.response.UserPojo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class Utils {
    public static boolean validEt(EditText etUserName, String strEtUserName) {
        if (strEtUserName.isEmpty()) {
            etUserName.setError("Field Empty");
            return false;
        }
        return true;
    }

    public static MultipartBody.Part fileRequest(File file, String image) {
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                file);
        return MultipartBody.Part.createFormData(image, file.getName(), fileReqBody);
    }


}

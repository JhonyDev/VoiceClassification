package com.app.voiceclassification.mvvm;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.voiceclassification.mvvm.capsules.response.RegResponse;
import com.app.voiceclassification.mvvm.interfaces.NetworkCalls;
import com.app.voiceclassification.mvvm.mapping_utils.GenericResponse;

import java.util.List;

public class MvvmUtils {

    public static NetworkCalls getNcs() {
        return APIClient.getRetrofit().create(NetworkCalls.class);
    }

    public static void printErrors(Context context, GenericResponse<RegResponse> regResponseGenericResponse) {
        StringBuilder errors = new StringBuilder();
        try {
            for (String err : regResponseGenericResponse.getErrorMessages()) {
                Log.i("TAG", "printErrors: " + err);
                errors.append(err).append("\n");
            }
            Toast.makeText(context, errors, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static void printGeneralErrors(Context context, List<String> strings) {
        StringBuilder errors = new StringBuilder();
        try {
            for (String err : strings)
                errors.append(err).append("\n");
            Toast.makeText(context, errors, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}

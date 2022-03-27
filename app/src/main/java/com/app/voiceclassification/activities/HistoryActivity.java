package com.app.voiceclassification.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.voiceclassification.R;
import com.app.voiceclassification.adapters.TypeRecyclerViewAdapter;
import com.app.voiceclassification.info.Info;
import com.app.voiceclassification.mvvm.MvvmUtils;
import com.app.voiceclassification.mvvm.capsules.Super;
import com.app.voiceclassification.mvvm.capsules.response.HistoryPojo;
import com.app.voiceclassification.mvvm.mapping_utils.GenericCall;
import com.app.voiceclassification.mvvm.mapping_utils.GenericResponse;
import com.app.voiceclassification.utils.DialogUtils;
import com.app.voiceclassification.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    List<Super> listInstances;
    Dialog loadingDialog;
    TypeRecyclerViewAdapter typeRecyclerViewAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.rv);
        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);

        initCaptures();
    }

    private void initCaptures() {
        listInstances = new ArrayList<>();
        typeRecyclerViewAdapter = new TypeRecyclerViewAdapter(this, listInstances, Info.RV_TYPE_CAPTURE);
        recyclerView.setAdapter(typeRecyclerViewAdapter);
        loadingDialog.show();

        new GenericCall<>(MvvmUtils.getNcs()
                .getHistory(SharedPrefUtils.getToken(this)))
                .getMutableLiveData()
                .observe(this, this::initImageResp);
    }

    private void initImageResp(GenericResponse<List<HistoryPojo>> listGenericResponse) {
        if (listGenericResponse.getResponseCode() == 401) {
            Toast.makeText(this, "Please Login again", Toast.LENGTH_SHORT).show();
            SharedPrefUtils.setToken(this, "");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        loadingDialog.dismiss();
        if (listGenericResponse.isSuccessful())
            initRv(listGenericResponse.getResponse());
        else
            MvvmUtils.printGeneralErrors(this, listGenericResponse.getErrorMessages());
    }

    private void initRv(List<HistoryPojo> response) {
        listInstances.clear();
        listInstances.addAll(response);
        typeRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void back(View view) {
        finish();
    }
}
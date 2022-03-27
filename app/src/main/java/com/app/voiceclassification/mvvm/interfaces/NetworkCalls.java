package com.app.voiceclassification.mvvm.interfaces;


import com.app.voiceclassification.mvvm.capsules.request.PostLoginPojo;
import com.app.voiceclassification.mvvm.capsules.request.PostRegPojo;
import com.app.voiceclassification.mvvm.capsules.response.HistoryPojo;
import com.app.voiceclassification.mvvm.capsules.response.PredicationRespCapsule;
import com.app.voiceclassification.mvvm.capsules.response.RegResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface NetworkCalls {

    @POST(Urls.URL_LOGIN)
    Call<RegResponse> postLogin(@Body PostLoginPojo postRegPojo);

    @POST(Urls.URL_REG)
    Call<RegResponse> postAuth(@Body PostRegPojo postRegPojo);

    @GET(Urls.URL_PREV_HISTORY)
    Call<List<HistoryPojo>> getHistory(@Header("Authorization") String token);

    @POST(Urls.URL_POST_IMAGE_PROCESS)
    @Multipart
    Call<PredicationRespCapsule> postPrediction(
            @Header("Authorization") String token,
            @Part MultipartBody.Part profileImage);


}
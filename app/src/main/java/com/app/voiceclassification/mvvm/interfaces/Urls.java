package com.app.voiceclassification.mvvm.interfaces;

public interface Urls {
    /**
     * CHANGE BASE_URL ACCORDING TO NEW CONFIGURATION
     */
    String BASE_URL = "http://4ced-119-160-68-123.ngrok.io";


    /**
     * Defining endpoints of server's API
     * */
    String URL_LOGIN = "/auth/login/";
    String URL_REG = "/auth/registration/";
    String URL_PREV_HISTORY = "/api/predication/";
    String URL_POST_PREDICTION = "/api/predication/";
    String URL_UPDATE_PROFILE = "/api/my/profile/";
}
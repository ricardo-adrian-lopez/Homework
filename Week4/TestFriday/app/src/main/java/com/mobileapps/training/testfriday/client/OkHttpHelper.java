package com.mobileapps.training.testfriday.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.mobileapps.training.testfriday.model.Acronym;
import com.mobileapps.training.testfriday.model.Lf;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper {

    private static final String TAG = "OkHttpHelper";

    private final String acronym;
    private final Handler handler;

    private OkHttpClient okHttpClient;
    private Request request;

    public OkHttpHelper(String acronym, Handler handler) {
        this.acronym = acronym;
        okHttpClient = new OkHttpClient();
        request = new Request.Builder().url(getUrl()).build();
        this.handler = handler;
    }

    private HttpUrl getUrl() {
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http")
                .host("www.nactem.ac.uk")
                .addPathSegment("software")
                .addPathSegment("acromine")
                .addPathSegment("dictionary.py")
                .addQueryParameter("sf",acronym)
                .build();
        Log.d(TAG, "getUrl: " + httpUrl.toString());
        return httpUrl;
    }

    public void makeAsyncCall(){
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                //Log.d(TAG, "onResponse: " + response.body().string());
                Acronym[] acronym = gson.fromJson(response.body().string(), Acronym[].class);
                ArrayList<Lf> myList = (ArrayList<Lf>) acronym[0].getLfs();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("listAcronyms",myList);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }




}

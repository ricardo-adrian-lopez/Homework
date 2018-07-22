package com.mobileapps.training.daily3.client;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mobileapps.training.daily3.model.Profile;
import com.mobileapps.training.daily3.model.Repo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public class ProfileHelper {

    private Handler handler;

    public ProfileHelper(Handler handler) {
        this.handler = handler;
    }

    private static final String TAG = "ProfileHelper";

    public static final String BASE_URL = "https://api.github.com/";

    public Retrofit createClient(){
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public Call<Profile> getProfile(String authToken){
        Retrofit retrofit = createClient();
        Service service = retrofit.create(Service.class);
        return service.getProfile(authToken);
    }

    public Call<List<Repo>> getProfileRepos(String authToken){
        Retrofit retrofit = createClient();
        Service service = retrofit.create(Service.class);
        return service.getProfileRepos(authToken);
    }

    interface Service{

        @GET("users/ricardo-adrian-lopez")
        Call<Profile> getProfile(@Header("Authorization")String authToken);

        @GET("users/ricardo-adrian-lopez/repos")
        Call<List<Repo>> getProfileRepos(@Header("Authorization") String authToken);
    }

    public void makeAsyncCall(final Context context, String authToken){
        Log.d(TAG, "makeAsyncCall: ");
        getProfile(authToken).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Profile profile = response.body();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelable("user",profile);
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void makeAsyncCallRepos(String authToken){
        Log.d(TAG, "makeAsyncCallRepos: ");
        getProfileRepos(authToken).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                ArrayList<Repo> repos = (ArrayList<Repo>) response.body();
                Log.d(TAG, "onResponse: " + repos);
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("repos",repos);
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }
}

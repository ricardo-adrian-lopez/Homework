package com.mobileapps.training.testfriday;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mobileapps.training.testfriday.model.Coffee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitHelper {

    private static final String TAG = "RetrofitHelper";

    public static final String BASE_URL = "https://demo6983184.mockable.io/";

    private Handler handler;
    public RetrofitHelper(Handler handler) {
        this.handler = handler;
    }


    public Retrofit createClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public void getListCoffe(){
        getList().enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                Log.d(TAG, "onResponse: RESPONSE: " + response.body());
                ArrayList<Coffee> list = (ArrayList<Coffee>) response.body();
                Log.d(TAG, "onResponse: " + list);

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("listCoffee",list);
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public Call<List<Coffee>> getList(){
        Retrofit retrofit = createClient();
        CoffeeService coffeeService = retrofit.create(CoffeeService.class);
        return coffeeService.getList();
    }

    public interface CoffeeService{
        @GET("coffees")
        Call<List<Coffee>> getList();
    }

}

package com.bam.mysqltest.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "http://192.168.1.109/laundry/";
    private static Retrofit retro;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    public static Retrofit konRetrofit() {
        if (retro == null) {
            retro= new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retro;
    }

    /*
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();*/
}

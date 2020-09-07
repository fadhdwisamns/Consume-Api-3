package com.fadh.cataloguemoveapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://www.omdbapi.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
//        if (retrofit== null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
        OkHttpClient client = new OkHttpClient.Builder().build();
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        gb.serializeNulls();
        // gb.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gb.create();


        retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .client(client)
                .build();



        return retrofit;        }
    }


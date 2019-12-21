package com.example.finalproject.Network;

import com.example.finalproject.Network.API.QuizApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

// we are calling retrofit http client for android and java
//include the Retrofit library and also Google's Gson library to convert JSON to java objects
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;

    // here is the base url
    //Retrofit needs a base URL to build its instance, so we will pass it a URL when calling RetrofitClient.getClient(String baseUrl)
    private static final String BASE_URL = "https://quiz-api-service.herokuapp.com/";
    private Retrofit mRetrofit;

    private NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public QuizApi getJSONApi() {
        return mRetrofit.create(QuizApi.class);
    }
}

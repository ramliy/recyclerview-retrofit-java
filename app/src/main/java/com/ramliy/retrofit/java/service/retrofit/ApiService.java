package com.ramliy.retrofit.java.service.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ramliy.retrofit.java.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramliy10 on 08/05/20.
 */
public class ApiService {
    private static ApiClient instance;
    private static Gson gson;

    public static synchronized ApiClient getInstance() {
        if (instance == null) {
            instance = initiate();
        }

        return instance;
    }
    private static ApiClient initiate() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        Retrofit retrofit = generateRetrofit(BuildConfig.BASE_URL, httpClient);
        return retrofit.create(ApiClient.class);
    }

    public static Retrofit generateRetrofit(String baseUrl, OkHttpClient.Builder httpClient) {
        Gson gson = getGsonInstance();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        if (!httpClient.interceptors().contains(logging)) {
            // add logging as interceptor
            httpClient.addInterceptor(logging);
        }

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }

    public static Gson getGsonInstance() {
        if (gson != null) return gson;

        return new GsonBuilder()
                .setLenient()
                .create();
    }

}

package com.desafiolatam.chucknorrisrules.networks;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dckurty on 26-11-17.
 */

public class Interceptors {

    private static final String BASE_URL = "https://matchilling-chuck-norris-jokes-v1.p.mashape.com/";

    public Facts aCommonGetInterceptor(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request request = originalRequest.newBuilder()
                    /*Common headers*/
                        .header("X-Mashape-Key", "gXnsz8WXcImshDz4hELa7lZ9KM2rp1LYw14jsn2wQEPnPjdwNU")
                        .header("accept", "application/json")
                    /*Custom header*/
                        //.header("Flavor", "mint")
                        .build();

                Response response = chain.proceed(request);

            /*If the request fail then you get 3 retrys*/
                int retryCount = 0;
                while (!response.isSuccessful() && retryCount < 3) {
                    retryCount++;
                    response = chain.proceed(request);
                }

                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Facts request = interceptor.create(Facts.class);

        return request;
    }

}

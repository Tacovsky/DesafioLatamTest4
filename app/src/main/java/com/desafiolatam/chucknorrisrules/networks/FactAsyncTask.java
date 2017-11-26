package com.desafiolatam.chucknorrisrules.networks;

import android.os.AsyncTask;

import com.desafiolatam.chucknorrisrules.models.Fact;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dckurty on 26-11-17.
 */

public class FactAsyncTask extends AsyncTask<String, Integer, Fact> {

    @Override
    protected Fact doInBackground(String... params) {
        Facts facts = new Interceptors().aCommonGetInterceptor();
        Call<Fact> call = facts.randomFact();

        try{
            Response<Fact> response = call.execute();
            if(200 == response.code() && response.isSuccessful()){
                return response.body();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

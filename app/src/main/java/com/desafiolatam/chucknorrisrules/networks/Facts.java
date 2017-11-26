package com.desafiolatam.chucknorrisrules.networks;

import com.desafiolatam.chucknorrisrules.models.Fact;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dckurty on 26-11-17.
 */

public interface Facts {

    @GET("jokes/random")
    Call<Fact> randomFact();
}

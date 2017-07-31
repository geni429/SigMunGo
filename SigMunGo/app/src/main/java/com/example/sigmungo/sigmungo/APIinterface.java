package com.example.sigmungo.sigmungo;

import com.example.sigmungo.sigmungo.Items.SignInRequest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by geni on 2017. 7. 28..
 */

interface APIinterface {
    @FormUrlEncoded
    @POST("/account/login")
    Call<SignInRequest> doSignIn(@Field("id") String id, @Field("password") String password);
}

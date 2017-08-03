package com.myoungchi.android.sigmungo;

import com.myoungchi.android.sigmungo.Items.SignInRequest;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by geni on 2017. 7. 28..
 */

interface APIinterface {
    @FormUrlEncoded
    @POST("/account/signin")
    Call<SignInRequest> doSignIn(@Field("id") String id,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("/account/signup")
    Call<Void> doSignUp(@Field("name") String name,
                                 @Field("phone") String phone,
                                 @Field("id") String id,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("/account/idcheck")
    Call<Void> doIdCheck(@Field("id") String id);

    @GET("/restaurant")
    Call<JsonObject> getRestaurantInfo();
}

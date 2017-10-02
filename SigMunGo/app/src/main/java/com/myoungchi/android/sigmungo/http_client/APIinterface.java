package com.myoungchi.android.sigmungo.http_client;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

/**
 * Created by geni on 2017. 7. 28..
 */

public interface APIinterface {
    //SignIn activity
    @FormUrlEncoded
    @POST("/account/signin")
    Call<Void> doSignIn(@Field("id") String id,
                        @Field("password") String password);

    //PhoneCertify activity
    @FormUrlEncoded
    @POST("/account/signup")
    Call<Void> doSignUp(@Field("name") String name,
                         @Field("phone") String phone,
                         @Field("id") String id,
                         @Field("password") String password);

    //SignUp activity
    @FormUrlEncoded
    @POST("/account/idcheck")
    Call<Void> doIdCheck(@Field("id") String id);

    //SignUp activity
    @FormUrlEncoded
    @POST("/account/phonecertify")
    Call<Void> doPhoneCertify(@Field("phone") String phone);

    //Main activity
    @GET("/restaurant")
    Call<JsonObject> getRestaurantInfo();

    @GET("/restaurant/detail/{contentId}")
    Call<JsonObject> getRestaurantDetail(@Path("contentId") String contentId);

    @GET("/restaurant/detail/images/{contentId}")
    Call<JsonObject> getRestaurantImgs(@Path("contentId") String contentId);

    //Main activity
    @GET("/userinfo/{id}")
    Call<JsonObject> getUserInfo(@Path("id") String id);

    //WriteResult activity
    @FormUrlEncoded
    @POST("/restaurant/post/{content_id}")
    Call<Void> doPost(@Path("content_id") String content_id,
                      @Field("id") String id,
                      @Field("post") String post);

    //MyPage activity
    @GET("/mypage/postlist/{id}")
    Call<JsonObject> getPostList(@Path("id") String id);

    //FindId
    @GET("/account/id")
    Call<JsonObject> getIdCertifyCode();

    //FindPw
    @PUT("/account/password")

    //get CertifyCode
    @POST("/certify/demand")
    Call<Void> getCertifyCode();
}

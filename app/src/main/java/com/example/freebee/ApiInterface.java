package com.example.freebee;

import com.example.freebee.models.CallRates.Packages;
import com.example.freebee.models.CreateEmail.CreateEmail;
import com.example.freebee.models.CreatePassword.CreatePassword;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/api/createuser?username=admin&password=Tringy%232020$&reseller=0&signup=0&otp=1")
    Call<JsonElement> createuser(
            @Query("createuser") String number, @Query("createpassword") String password);

    @POST("/api/createuser?username=admin&password=Tringy%232020$&reseller=0&signup=0&otp=2")
    Call<JsonElement> createuser2(
            @Query("createuser") String number, @Query("createpassword") String password);

    @POST("/api/activateaccount")
    Call<JsonElement> activateaccount(
            @Query("username") String number,
            @Query("password") String password,
            @Query("otp") String otp);

    @GET("/api/getavailablebalance")
    Call<JsonElement> getavailablebalance(@Query ("username")String username,@Query("password") String password);

    @POST("/api/user")
    Call<JsonElement> user(@Query("projectid")String projectid,@Query("authtoken")String authtoken,@Query("username")String username,@Query("password")String password);

    @POST("/api/recharge")
    Call<JsonElement> recharge(
            @Query("username") String username,
            @Query("password") String password,
            @Query("pinno") String pinno);
    @GET("/api/getpackages")
    Call<Packages> getPackages(@Query("username")String username,@Query("password")String password,@Query("packagename")String packagename);

    @POST("/api/updatepassword")
    Call<CreatePassword> createPassword(@Query("username")String username,@Query("password")String password,@Query("newpassword")String newpassword);

    @POST("/api/setemailid")
    Call<CreateEmail> setEmail(@Query("username")String username,@Query("password")String password,@Query("emailid")String emailid);
}
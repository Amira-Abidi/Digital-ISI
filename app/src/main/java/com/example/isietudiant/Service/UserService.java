package com.example.isietudiant.Service;

import com.example.isietudiant.Models.User;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST("loginservice")
    Call<ResponseBody> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("loginservice")
    Call<ResponseBody> loginUser(
            @FieldMap Map<String, String> map
    );

    @GET("API/system/session/unusedId")
    Call<User> getUserId();

}

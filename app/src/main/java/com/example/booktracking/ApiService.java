package com.example.booktracking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("signup")
    @FormUrlEncoded
    Call<AuthMessage> signup(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("password_confirm") String password_confirm);

    @POST("login")
    @FormUrlEncoded
    Call<AuthMessage> login(@Field("email") String email, @Field("password") String password);

    @POST("b/list")
    @FormUrlEncoded
    Call<List<Book>> getBooks(@Field("content") String content, @Field("field") String field);

    @GET("b/detail/{id}")
    Call<Book> getBookbyId(@Path("id") int id);

    @GET("user/{id}")
    Call<User> getUser(@Path("id") int id);

    @GET("record/{id}")
    Call<List<Record>> getRecords(@Path("id") int id);
}

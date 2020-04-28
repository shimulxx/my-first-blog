package com.example.jobproject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitClient {
    String BASE_URL = "https://reqres.in/api/";

    @GET("users")
    Call<Details> getDetails();
}

package com.helo.kwp.mapas.api;

import com.helo.kwp.mapas.response.PointsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PointsApiService {

    @GET("page/{page}")
    Call<PointsResponse> getPoints(@Path("page")  Integer page, @Header("Authorization") String authHeader);

}

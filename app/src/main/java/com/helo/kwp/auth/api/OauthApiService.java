package com.helo.kwp.auth.api;

import com.helo.kwp.mapas.response.PointsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface OauthApiService {

    @GET("token")
    Call<PointsResponse> getToken(@Header("Authorization") String authHeader);

}

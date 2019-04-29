package com.helo.kwp.mapas.controller;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.helo.kwp.R;
import com.helo.kwp.adapter.OnItemClickListener;
import com.helo.kwp.adapter.PointsAdapter;
import com.helo.kwp.mapas.api.PointsApiService;
import com.helo.kwp.mapas.model.Point;
import com.helo.kwp.mapas.response.PointsResponse;
import com.helo.kwp.util.BaseURLConstants;
import com.helo.kwp.util.RetrofitClient;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PointController extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "POINTS: ";

    private PointsAdapter listaPagosAdapter;
    private RecyclerView recyclerView;
    private PointsApiService service;
    private Dialog customDialog;

    private int offset;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);

        service = RetrofitClient
                .getClient(BaseURLConstants.POINTS_URL)
                .create(PointsApiService.class);

        aptoParaCargar = true;
        offset = 0;
        consumeService(service.getPoints(0));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        listaPagosAdapter = new PointsAdapter(this);
        listaPagosAdapter.setClickListener(this);
        recyclerView.setAdapter(listaPagosAdapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Log.e(TAG, " onErrorResponse: " );
    }

    @Override
    public void onClick(View view, int position) {
        /*
        customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.custom_pop_pagos);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();*/
    }

    public void consumeService(Call respuestaCall) {
        System.out.println(respuestaCall.request());
        respuestaCall.enqueue(new Callback<PointsResponse>() {
            @Override
            public void onResponse(Call<PointsResponse> call, Response<PointsResponse> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()) {
                    Log.e(TAG, " onResponseSuccess: " + new Gson().toJson(response));
                    ArrayList<Point> responseList = response.body().getAjaxResult();
                    listaPagosAdapter.getPointList(responseList);
                } else {
                    Log.e(TAG, " ==========================================>>>>>>>>>>>>>>>>>>>>>onResponseError: " + response.code());
                    Log.e(TAG, " ==========================================>>>>>>>>>>>>>>>>>>>>>onResponseError: " + new Gson().toJson(response));
                    Log.e(TAG, " ==========================================>>>>>>>>>>>>>>>>>>>>>onErrorResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PointsResponse> call, Throwable t) {
                Log.e(TAG, " onFailure: " + new Gson().toJson(call.request()));
                t.printStackTrace();
            }
        });
    }


}

package com.bam.mysqltest.API;

import com.bam.mysqltest.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetreieveData();
}


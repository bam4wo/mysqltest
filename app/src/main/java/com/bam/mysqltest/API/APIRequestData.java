package com.bam.mysqltest.API;

import com.bam.mysqltest.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetreieveData();


    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreate(
            @Field("name") String name,
            @Field("alamat") String alamat,
            @Field("telepon") String telepon
    );

    @FormUrlEncoded
    @POST("delect.php")
    Call<ResponseModel> ardDelect(
            @Field("id") int id
    );
}


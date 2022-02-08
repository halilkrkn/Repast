package com.example.repast.data.api

import com.example.repast.data.model.YemekResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface FoodsApiService {

    @GET("yemekler/tumYemekleriGetir.php")
    fun getAllFoods(): Call<YemekResponse>
}
package com.example.repast.data.api

import com.example.repast.data.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsApiService {

    @GET("yemekler/tumYemekleriGetir.php")
    fun getAllFoods(): Call<YemekResponse>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun postAddFoodsCard(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String,
    ): Call<SepetResponse>


    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun postFoodsListCard(
        @Field("kullanici_adi") kullanici_adi: String,
    ): Call<CartFoodResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFoodsListCard(
        @Field("sepet_yemek_id") sepet_yemek_id:Int,
        @Field("kullanici_adi") kullanici_adi: String,
    ): Call<SepetResponse>
}
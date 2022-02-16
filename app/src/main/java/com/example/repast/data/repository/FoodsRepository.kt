package com.example.repast.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.repast.data.api.FoodsApiService
import com.example.repast.data.db.FoodsDatabase
import com.example.repast.data.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodsRepository @Inject constructor(
    val foodsApi: FoodsApiService,
    val foodsDB: FoodsDatabase
) {

    // RETROFİT İŞLEMLERİ
    fun getAllFoods() = foodsApi.getAllFoods()

    fun getAllFoodsListCard(kullanici_adi:String) = foodsApi.postFoodsListCard(kullanici_adi)

    fun postAddFoodsCard(yemek_adi:String, yemek_resim_adi:String,yemek_fiyat: Int,yemek_siparis_adet: Int,kullanici_adi: String) =
        foodsApi.postAddFoodsCard(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    fun deleteFoodsListCard(sepet_yemek_id:Int, kullanici_adi: String) =
        foodsApi.deleteFoodsListCard(sepet_yemek_id, kullanici_adi)


    // DATABASE İŞLEMLERİ
    suspend fun insertFood(foods:Yemekler) = foodsDB.getFoodsDao().insertFoods(foods)

    suspend fun deleteFood(foods:Yemekler) = foodsDB.getFoodsDao().deleteFoods(foods)

    fun saveFoods() = foodsDB.getFoodsDao().getAllFoods()

    fun searchFoods(searchQuery:String) = foodsDB.getFoodsDao().searchSavedName(searchQuery)

}
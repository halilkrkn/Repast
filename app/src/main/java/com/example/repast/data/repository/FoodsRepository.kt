package com.example.repast.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.repast.data.api.FoodsApiService
import com.example.repast.data.model.SepetResponse
import com.example.repast.data.model.YemekResponse
import com.example.repast.data.model.Yemekler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodsRepository @Inject constructor(
    val foodsApi: FoodsApiService
) {

    var foodLists: MutableLiveData<List<Yemekler>> = MutableLiveData()


    fun getFoods(): MutableLiveData<List<Yemekler>>{
        return foodLists
    }

    fun getAllFoods(){
        foodsApi.getAllFoods().enqueue(object: Callback<YemekResponse>{
            override fun onResponse(call: Call<YemekResponse>, response: Response<YemekResponse>) {
               val foodList = response.body()?.yemekler
                foodLists.value = foodList!!
            }
            override fun onFailure(call: Call<YemekResponse>, t: Throwable) {}
        })
    }

//    suspend fun postAddFoodsCard(yemek_adi:String, yemek_resim_adi:String,yemek_fiyat: Int,yemek_siparis_adet: Int,kullanici_adi: String) =    foodsApi.postAddFoodsCard(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    fun postAddFoodsCard(yemek_adi:String, yemek_resim_adi:String,yemek_fiyat: Int,yemek_siparis_adet: Int,kullanici_adi: String){
        foodsApi.postAddFoodsCard(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(object : Callback<SepetResponse>{
            override fun onResponse(call: Call<SepetResponse>?, response: Response<SepetResponse>?) {}
            override fun onFailure(call: Call<SepetResponse>?, t: Throwable?) {}
        })

    }



}
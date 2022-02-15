package com.example.repast.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.repast.data.api.FoodsApiService
import com.example.repast.data.model.*
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
    var foodListCard: MutableLiveData<List<SepetYemekler>> = MutableLiveData()


    fun getFoods(): MutableLiveData<List<Yemekler>>{
        return foodLists
    }

    fun getFoodsListCard(): MutableLiveData<List<SepetYemekler>>{
        return foodListCard
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

    fun getAllFoodsListCard(kullanici_adi:String) = foodsApi.postFoodsListCard(kullanici_adi).enqueue(object: Callback<CartFoodResponse>{
        override fun onResponse(
            call: Call<CartFoodResponse>,
            response: Response<CartFoodResponse>
        ) {
            val response = response.body()
            foodListCard.value = response?.sepetYemekler
        }

        override fun onFailure(call: Call<CartFoodResponse>, t: Throwable) {
            Log.v("SEPETGETIR",t.message.toString())
        }

    })


//    suspend fun postAddFoodsCard(yemek_adi:String, yemek_resim_adi:String,yemek_fiyat: Int,yemek_siparis_adet: Int,kullanici_adi: String) =    foodsApi.postAddFoodsCard(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    fun postAddFoodsCard(yemek_adi:String, yemek_resim_adi:String,yemek_fiyat: Int,yemek_siparis_adet: Int,kullanici_adi: String){
        foodsApi.postAddFoodsCard(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(object : Callback<SepetResponse>{
            override fun onResponse(call: Call<SepetResponse>?, response: Response<SepetResponse>?) {}
            override fun onFailure(call: Call<SepetResponse>?, t: Throwable?) {}
        })
    }

    fun deleteFoodsListCard(sepet_yemek_id:Int, kullanici_adi: String){
        foodsApi.deleteFoodsListCard(sepet_yemek_id, kullanici_adi).enqueue(object : Callback<SepetResponse>{
            override fun onResponse(call: Call<SepetResponse>?, response: Response<SepetResponse>?) {
                getAllFoodsListCard(kullanici_adi)
            }
            override fun onFailure(call: Call<SepetResponse>?, t: Throwable?) {}
        })
    }



}
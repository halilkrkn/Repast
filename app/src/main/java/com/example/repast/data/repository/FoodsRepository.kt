package com.example.repast.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.repast.data.api.FoodsApiService
import com.example.repast.data.model.YemekResponse
import com.example.repast.data.model.Yemekler
//import com.example.repast.data.paging.FoodsPagingSource
import com.example.repast.utils.Constants
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



}
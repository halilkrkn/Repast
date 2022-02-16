package com.example.repast.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repast.data.model.CartFoodResponse
import com.example.repast.data.model.SepetResponse
import com.example.repast.data.model.SepetYemekler
import com.example.repast.data.repository.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FoodCartListViewModel @Inject constructor(
    val repository: FoodsRepository
):ViewModel(){
    var foodListCard: MutableLiveData<List<SepetYemekler>> = MutableLiveData()


    init {
        getFoodsListCard()
    }


    fun getFoodsListCard(): MutableLiveData<List<SepetYemekler>>{
        return foodListCard
    }

    fun getAllFoodsListCard(kullanici_adi:String) = repository.getAllFoodsListCard(kullanici_adi).enqueue(object: Callback<CartFoodResponse>{
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


    fun deleteFoodsListCard(sepet_yemek_id:Int, kullanici_adi: String){
        repository.deleteFoodsListCard(sepet_yemek_id,kullanici_adi).enqueue(object :
            Callback<SepetResponse> {
            override fun onResponse(call: Call<SepetResponse>?, response: Response<SepetResponse>?) {
                repository.getAllFoodsListCard(kullanici_adi)
            }
            override fun onFailure(call: Call<SepetResponse>?, t: Throwable?) {}
        })
    }

}
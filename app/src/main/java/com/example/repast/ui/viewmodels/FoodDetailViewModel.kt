package com.example.repast.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repast.data.model.SepetResponse
import com.example.repast.data.model.Yemekler
import com.example.repast.data.repository.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    val repository: FoodsRepository
):ViewModel(){

//    fun postAddFoodsCard(yemek_adi:String, yemek_resim_adi:String,yemek_fiyat: Int,yemek_siparis_adet: Int,kullanici_adi: String) = viewModelScope.launch(Dispatchers.Main){
//        repository.postAddFoodsCard(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(object : Callback<SepetResponse>{
//            override fun onResponse(call: Call<SepetResponse>, response: Response<SepetResponse>) {}
//            override fun onFailure(call: Call<SepetResponse>, t: Throwable) {}
//        })
//    }
    fun postAddFoodsCard(yemek_adi:String, yemek_resim_adi:String,yemek_fiyat: Int,yemek_siparis_adet: Int,kullanici_adi: String){
        repository.postAddFoodsCard(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

}
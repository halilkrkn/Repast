package com.example.repast.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repast.data.model.SepetYemekler
import com.example.repast.data.repository.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodCartListViewModel @Inject constructor(
    val repository: FoodsRepository
):ViewModel(){
    var foodCardList = MutableLiveData<List<SepetYemekler>>()

    init {
        foodCardList = repository.getFoodsListCard()
    }
//
//    fun getAllFoods() = viewModelScope.launch(Dispatchers.Main){
//        foodList.value = repository.getAllFoods()
//    }

    fun getAllFoods(kullanici_adi:String){
        repository.getAllFoodsListCard(kullanici_adi)
    }
}
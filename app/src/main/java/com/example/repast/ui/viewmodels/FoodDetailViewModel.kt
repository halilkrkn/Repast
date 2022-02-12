package com.example.repast.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repast.data.model.Yemekler
import com.example.repast.data.repository.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    val repository: FoodsRepository
):ViewModel(){

    var foodDetail= MutableLiveData<Yemekler>()

    init {
        getAllFoods()
        foodDetail = repository.getFoodsDetail()
    }
//
//    fun getAllFoods() = viewModelScope.launch(Dispatchers.Main){
//        foodList.value = repository.getAllFoods()
//    }

    fun getAllFoods(){
        repository.yemekleriAl()
    }

}
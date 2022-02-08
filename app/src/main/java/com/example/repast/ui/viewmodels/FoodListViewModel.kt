package com.example.repast.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.repast.data.model.YemekResponse
import com.example.repast.data.model.Yemekler
import com.example.repast.data.repository.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel  @Inject constructor(
    val repository: FoodsRepository
): ViewModel(){

    var foodList = MutableLiveData<List<Yemekler>>()

    init {
        getAllFoods()
        foodList = repository.getFoods()
    }
//
//    fun getAllFoods() = viewModelScope.launch(Dispatchers.Main){
//        foodList.value = repository.getAllFoods()
//    }

    fun getAllFoods(){
        repository.getAllFoods()
    }

}
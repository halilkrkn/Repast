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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel  @Inject constructor(
    val repository: FoodsRepository
): ViewModel(){

    var foodLists: MutableLiveData<List<Yemekler>> = MutableLiveData()

    init {
        getFoods()
        getAllFoods()
    }

    fun getFoods(): MutableLiveData<List<Yemekler>>{
        return foodLists
    }

    fun getAllFoods() = repository.getAllFoods().enqueue(object: Callback<YemekResponse> {
        override fun onResponse(call: Call<YemekResponse>, response: Response<YemekResponse>) {
            val foodList = response.body()?.yemekler
            foodLists.value = foodList!!
        }
        override fun onFailure(call: Call<YemekResponse>, t: Throwable) {}
    })

    fun insertFoods(foods:Yemekler) = viewModelScope.launch(Dispatchers.IO){
        repository.insertFood(foods)
    }
}



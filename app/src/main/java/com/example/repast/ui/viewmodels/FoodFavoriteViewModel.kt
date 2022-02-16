package com.example.repast.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.repast.data.model.Yemekler
import com.example.repast.data.repository.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodFavoriteViewModel @Inject constructor(
    val repository: FoodsRepository
): ViewModel() {

    fun insertFood(foods: Yemekler) = viewModelScope.launch(Dispatchers.IO){
        repository.insertFood(foods)
    }

    fun deleteFood(foods: Yemekler) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteFood(foods)
    }

    fun getAllFavoriFood() = repository.saveFoods()


    val searchQuery = MutableStateFlow("")

    val searhFoodFlow = searchQuery.flatMapLatest { searchQuery ->
        repository.searchFoods(searchQuery)
    }.asLiveData()

    fun searchFavoriFood(querySearch: String){
        searchQuery.value = querySearch
    }



}
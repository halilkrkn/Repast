package com.example.repast.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.repast.data.model.Yemekler
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodsDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoods(foods: Yemekler)
    
    @Delete
    suspend fun deleteFoods(foods:Yemekler)

    @Query("SELECT * FROM foods")
    fun getAllFoods(): LiveData<List<Yemekler>>

    //Saved Sayfası içerisindeki searh işlemi yapabilmemiz için database'e sorgu yazdık.
    @Query("SELECT * FROM foods WHERE yemek_adi LIKE '%' || :searchQuery || '%' ORDER BY yemek_adi DESC")
    fun searchSavedName(searchQuery: String): Flow<List<Yemekler>>
    
}
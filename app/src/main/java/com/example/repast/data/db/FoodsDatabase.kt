package com.example.repast.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.repast.data.model.Yemekler

@Database(
    entities = [Yemekler::class],
    version = 3,
    exportSchema = false
)
abstract class FoodsDatabase: RoomDatabase() {
    abstract fun getFoodsDao(): FoodsDao
}